package com.wlma.dao;

import com.wlma.dao.bean.CardInfo;

public interface CardDAO {

	public void insert(CardInfo cardInfo) throws Exception;

	public void update(CardInfo cardInfo) throws Exception;

	public String[][] queryAll() throws Exception;
}
