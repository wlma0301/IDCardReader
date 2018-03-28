package com.wlma.dao;

import com.wlma.dao.impl.CardDAOImpl;
/**
 * Describe：DAO工厂，有效避免由于DAO实现改变导致的大量代码修改
 * @author wlma
 * 
 */
public class DAOFactory {

	public static CardDAO getGuessDAOImpl() {
		return new CardDAOImpl();
	}
}
