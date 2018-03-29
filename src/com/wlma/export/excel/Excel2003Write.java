package com.wlma.export.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.util.IOUtils;

import com.wlma.export.Write;

public class Excel2003Write implements Write {

	private String filePath = null;
	private File file = null;
	private FileInputStream is = null;
	private FileOutputStream os = null;
	private HSSFWorkbook workbook = null;
	private XRow[] xRowList = null;	//数据
	private HSSFSheet sheet = null;
	private HSSFRow row = null;
	private HSSFCell cell = null;

	public Excel2003Write(String filePath, XRow[] xRowList) {
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
				workbook = new HSSFWorkbook();
	        } else {
	        	is = new FileInputStream(file);
				workbook = new HSSFWorkbook(is);
	        }
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
		/**
		 * 对已经存在的EXCEL要在原有数据的基础上填充数据
		 * 如果是新建EXCEL则所有元素都要新建
		 */
		if (workbook.getNumberOfSheets() > 0) {
			sheet = workbook.getSheetAt(0);
		} else {
			sheet = workbook.createSheet();
		}
		int rowNum = sheet.getLastRowNum();
		for (int i = 0; i < xRowList.length; i ++) {
			XRow xRow = xRowList[i];
			if (xRow != null) {
				if (xRow.getRowIndex() <= rowNum) {
					row = sheet.getRow(xRow.getRowIndex());
					if (row == null) row = sheet.createRow(xRow.getRowIndex());
				} else {	//如果填充的数据超出EXCEL原有最大行数，则新建一行
					row = sheet.createRow(xRow.getRowIndex());
				}
				XCell[] xCellList = xRow.getXCells();
				for (int j = 0; j < xCellList.length; j ++) {
					XCell xCell = xCellList[j];
					if (xCell != null) {
						cell = row.createCell(xCell.getColumn());
						cell.setCellValue((String) xCell.getValue());
					}
				}
			}
		}
		save();
	}

	@Override
	public void writeInfo() {
		/**
		 * 对已经存在的EXCEL要在原有数据的基础上填充数据
		 * 如果是新建EXCEL则所有元素都要新建
		 */
		if (workbook.getNumberOfSheets() > 0) {
			sheet = workbook.getSheetAt(0);
		} else {
			sheet = workbook.createSheet();
		}
		int rowNum = sheet.getLastRowNum();
		XRow xRow = xRowList[0];
		if (xRow != null) {
			XCell[] xCellList = xRow.getXCells();
			for (int j = 0; j < xCellList.length; j ++) {
				XCell xCell = xCellList[j];
				if (xCell.getRow() <= rowNum) {
					row = sheet.getRow(xCell.getRow());
					if (row == null) row = sheet.createRow(xCell.getRow());
				} else {	//如果填充的数据超出EXCEL原有最大行数，则新建一行
					row = sheet.createRow(xCell.getRow());
				}
				if (xCell != null) {
					cell = row.createCell(xCell.getColumn());
					cell.setCellValue((String) xCell.getValue());
				}
			}
		}
		save();
	}

	@Override
	public void wirteImage(XCell xCell) {
		try {
			InputStream in = (InputStream) xCell.getValue();
			byte[] bytes = IOUtils.toByteArray(in);
			int pictureIdx = workbook.addPicture(bytes, workbook.PICTURE_TYPE_JPEG);
			in.close();
			
			/**
			 * 对已经存在的EXCEL要在原有数据的基础上填充数据
			 * 如果是新建EXCEL则所有元素都要新建
			 */
			if (workbook.getNumberOfSheets() > 0) {
				sheet = workbook.getSheetAt(0);
			} else {
				sheet = workbook.createSheet();
			}

			//创建一个顶级容器
			Drawing drawing = sheet.createDrawingPatriarch();
			CreationHelper helper = workbook.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();

			row = sheet.getRow(xCell.getRow());
			if (row == null) {
				row = sheet.createRow(xCell.getRow());
			}
			cell = row.createCell(xCell.getColumn());

			anchor.setCol1(xCell.getColumn());
			anchor.setRow1(xCell.getRow());
			Picture pict = drawing.createPicture(anchor, pictureIdx);
			//auto-size picture relative to its top-left corner
			pict.resize();//该方法只支持JPEG 和 PNG后缀文件
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		save();
	}

	@Override
	public void save() {
		try {
			os = new FileOutputStream(filePath, false);
			workbook.write(os);
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