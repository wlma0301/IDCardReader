package com.wlma.export.excel;

public class XCell {

	private int no;
	private int row;
	private int column;
	private Object value = null;

	public XCell(Object value) {
		this.value = value;
	}

	public XCell(int no, int column, Object value) {
		this.no = no;
		this.column = column;
		this.value = value;
	}

	public XCell(int no, int row, int column, Object value) {
		this.no = no;
		this.row = row;
		this.column = column;
		this.value = value;
	}

	public int getNo() {
		return no;
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