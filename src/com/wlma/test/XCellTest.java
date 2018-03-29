package com.wlma.test;

import java.util.HashMap;
import java.util.Map;

import com.wlma.export.excel.XCell;
import com.wlma.export.excel.XRow;

public class XCellTest {

	public static void main(String[] args) {
		XCell xCell = new XCell(0, 0, "xxxxx");
		System.out.println(xCell.getNo());
		System.out.println(xCell.getColumn());
		System.out.println(xCell.getRow());
		System.out.println(xCell.getValue());
//		XRow xRow = new XRow(0);
//		xRow.addXCell(xCell);
//		System.out.println(xRow.getXCellValue(0));
		Map<Integer, XCell> xCells = new HashMap<Integer, XCell>();
		xCells.put(1, xCell);
	}
}
