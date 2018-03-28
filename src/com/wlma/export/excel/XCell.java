package com.wlma.export.excel;

public class XCell {

	private int row;
	private int column;
	private Object value = null;

	public XCell(Object value) {
		this.value = value;
	}

	public XCell(int column, Object value) {
		this.column = column;
		this.value = value;
	}

	public XCell(int row, int column, Object value) {
		this.row = row;
		this.column = column;
		this.value = value;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Object getValue() {
		return value;
	}

}