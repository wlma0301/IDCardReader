package com.wlma.export.excel;

import java.util.Map;

public class XRow {

	private int rowIndex;
	private Map<Object, XCell> xCells;

	public XRow(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public XRow addXCell(XCell xCell) {
		this.xCells.put(xCell.getColumn(), xCell);
		return this;
	}

	public XRow removeXCell(XCell xCell) {
		this.xCells.remove(xCell.getColumn());
		return this;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public XCell[] getXCells() {
		return this.xCells.values().toArray(new XCell[0]);
	}

	public XCell getXCell(int xCellColumn) {
		XCell xCell = this.xCells.get(xCellColumn);
		return xCell;
	}

	public Object getXCellValue(int xCellColumn) {
		XCell xCell = this.xCells.get(xCellColumn);
		if (xCell == null) {
			return xCellColumn;
		}
		return xCell.getValue();
	}
}
