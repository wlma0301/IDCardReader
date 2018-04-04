package com.wlma.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	private final String driver = "com.hxtt.sql.access.AccessDriver";
	private String classPath = DBConnection.class.getClassLoader().getResource("").getPath().replace("/bin", "");
	//private String url = "jdbc:Access:///e:/Workspace/CardReader/carddb1.mdb";
	private String url;
	private final String database = "carddb.mdb";
	private final String user = "";
    private final String password = "";
    private Connection conn = null;

    private DBConnection() {
		try {
			url = "jdbc:Access://" + classPath + database;
			Class.forName(driver);	//��������
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}

	private static class SingletonHolder {
		private static DBConnection instance = new DBConnection();
	}

	public static DBConnection getInstance() {
		return SingletonHolder.instance;
	}
	
	private Statement createStatement() throws SQLException {
		return conn.createStatement();
	}

	public void close() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    
}
