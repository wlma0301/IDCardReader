package com.wlma.export.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.wlma.export.Write;
import com.wlma.export.excel.XCell;
import com.wlma.export.excel.XRow;


public class Word2007Write implements Write {

	private String filePath = null;
	private File file = null;
	private FileInputStream is = null;
	private FileOutputStream os = null;
	private XWPFDocument document = null;
	private XRow[] xRowList = null;	//数据
	private XWPFTable table = null;
	private XWPFTableRow tableRow = null;
	private XWPFTableCell tableCell = null;
	private XWPFParagraph paragraph = null;
	private XWPFRun run = null;

	public Word2007Write(String filePath, XRow[] xRowList) {
		this.filePath = filePath;
		this.xRowList = xRowList;
		init();
	}

	@Override
	public void init() {
		file = new File(this.filePath);
		try {
	        if (!file.exists()) {
	        	//out
				file.createNewFile();
	        }
	        is = new FileInputStream(file);
	        document = new XWPFDocument(is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void writeList() {
		writeInfo();
	}

	@Override
	public void writeInfo() {
		table = document.getTableArray(0);	//获取第一个表格
		if (table != null) {
			XRow xRow = xRowList[0];
			if (xRow != null) {
				XCell[] xCellList = xRow.getXCells();
				for (int j = 0; j < xCellList.length; j ++) {
					XCell xCell = xCellList[j];
					tableRow = table.getRow(xCell.getRow());
					if (xCell != null) {
						tableCell = tableRow.getCell(xCell.getColumn());
						tableCell.setText((String) xCell.getValue());
					}
				}
			}
		}
		save();
	}

	@Override
	public void wirteImage(XCell xCell) {
		InputStream in = (InputStream) xCell.getValue();
		table = document.getTableArray(0);	//获取第一个表格
		if (table != null) {
			tableRow = table.getRow(xCell.getRow());
			if (tableRow != null) {
				tableCell = tableRow.getCell(xCell.getColumn());
				if (tableCell != null) {
					paragraph = tableCell.addParagraph();
					run = paragraph.createRun();
					//run.setText("xixi");
					//run.addBreak();
					try {
						run.addPicture(in, XWPFDocument.PICTURE_TYPE_JPEG, "", Units.toEMU(84), Units.toEMU(90));
					} catch (InvalidFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					run.addBreak(BreakType.PAGE);
				}
			}
		}
		save();
	}

	@Override
	public void save() {
		try {
			os = new FileOutputStream(filePath, false);
			document.write(os);
			os.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}