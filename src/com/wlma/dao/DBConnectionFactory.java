package com.wlma.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionFactory {

	public static Connection getConnection() throws SQLException {  
        Connection conn =  DBConnection.getInstance().getConnection();  
        return conn;  
    }  
}