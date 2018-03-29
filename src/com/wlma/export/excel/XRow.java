package com.wlma.export.excel;

import java.util.HashMap;
import java.util.Map;

public class XRow {

	private int rowIndex;
	private Map<Integer, XCell> xCells = new HashMap<Integer, XCell>();

	public XRow(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public XRow addXCell(XCell xCell) {
		this.xCells.put(xCell.getNo(), xCell);
		return this;
	}

	public XRow removeXCell(XCell xCell) {
		this.xCells.remove(xCell.getNo());
		return this;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public XCell[] getXCells() {
		return this.xCells.values().toArray(new XCell[0]);
	}

	public XCell getXCell(int xCellNo) {
		XCell xCell = this.xCells.get(xCellNo);
		return xCell;
	}

	public Object getXCellValue(int xCellNo) {
		XCell xCell = this.xCells.get(xCellNo);
		if (xCell == null) {
			return xCellNo;
		}
		return xCell.getValue();
	}
}