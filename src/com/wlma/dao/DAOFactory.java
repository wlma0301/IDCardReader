package com.wlma.dao;

import com.wlma.dao.impl.CardDAOImpl;
/**
 * Describe��DAO��������Ч��������DAOʵ�ָı䵼�µĴ��������޸�
 * @author wlma
 * 
 */
public class DAOFactory {

	public static CardDAO getGuessDAOImpl() {
		return new CardDAOImpl();
	}
}
