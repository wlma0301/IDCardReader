package com.wlma.export.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;

import com.wlma.export.Write;
import com.wlma.export.excel.XCell;
import com.wlma.export.excel.XRow;


public class Word2003Write implements Write {

	private String filePath = null;
	private File file = null;
	private FileInputStream is = null;
	private FileOutputStream os = null;
	private HWPFDocument document = null;
	private XRow[] xRowList = null;	//数据
	private Range range = null;
	private Table table = null;
	private TableRow tableRow = null;
	private TableCell tableCell = null;
	private Paragraph paragraph = null;

	public Word2003Write(String filePath, XRow[] xRowList) {
		this.filePath = filePath;
		this.xRowList = xRowList;
		init();
	}

	@Override
	public void init() {
		file = new File(this.filePath);
		try {
	        if (!file.exists()) {
				file.createNewFile();
	        }
	        is = new FileInputStream(file);
	        document = new HWPFDocument(is);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void writeInfo() {
		range = document.getRange();
		table = new TableIterator(range).next();

		if (table != null) {
			int rowNum = table.numRows();
			XRow xRow = xRowList[0];
			if (xRow != null) {
				XCell[] xCellList = xRow.getXCells();
				for (int j = 0; j < xCellList.length; j ++) {
					XCell xCell = xCellList[j];
					if (xCell.getRow() <= rowNum) {
						tableRow = table.getRow(xCell.getRow());
					} else {	//填充的数据超出最大行数
						System.out.println("无此行");
					}
					if (xCell != null) {
						tableCell = tableRow.getCell(xCell.getColumn());
						System.out.println((String) xCell.getValue());
						if (tableCell != null) {
							paragraph = tableCell.getParagraph(0);
							paragraph.replaceText(paragraph.text(), (String) xCell.getValue());
						}
					}
				}
			}
		}
		save();
	}

	@Override
	public void wirteImage(XCell xCell) {
		// TODO Auto-generated method stub

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