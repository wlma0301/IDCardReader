package com.wlma.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTest {

	static Workbook workBook = null;

	public static void main(String[] args) {
		File file = new File("C:/±Òºõ.xlsx");
		//File file = new File("C:/ID180326.XLS");
		try {
			workBook = new XSSFWorkbook(file);
			XSSFSheet sheet = (XSSFSheet) workBook.getSheetAt(0);
			XSSFRow row = sheet.getRow(1);
			XSSFCell cell = row.getCell(6);
			System.out.println(cell.getStringCellValue());
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}