package com.wlma.export;

import com.wlma.export.excel.XCell;

public abstract interface Write {

	public abstract void init();
	
	public abstract void writeList();

	public abstract void writeInfo();

	public abstract void wirteImage(XCell xCell);
	
	public abstract void save();
}
