package com.wlma.export.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWrite {

	private static final String EXTENSION_XLS = "xls";
    private static final String EXTENSION_XLSX = "xlsx";
    private String exportMode = "List";	//List¡¢Info
    private String filePath = "";

    public ExcelWrite(String exportMode, String filePath) {
    	this.exportMode = exportMode;
    	this.filePath = filePath;
    }

    private Workbook getWorkbook() throws IOException {
        Workbook workbook = null;
        File file = new File(this.filePath);
        if (!file.exists()) {
        	file.createNewFile();
        }
        InputStream is = new FileInputStream(file);
        if (this.filePath.toLowerCase().endsWith(EXTENSION_XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (this.filePath.toLowerCase().endsWith(EXTENSION_XLSX)) {
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    private void writeExcel() {
    	
    }

    private void write2007Excel() throws IOException {
    	XSSFWorkbook workbook = (XSSFWorkbook) getWorkbook();
    	XSSFSheet sheet = workbook.getSheetAt(0);
    	XSSFRow row = sheet.getRow(1);
    }
}
