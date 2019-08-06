package com.tricon.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCStep1 {
	
	private static final String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
	private static final String user = "postgres";
	private static final String password = "admin";

	public static void main(String[] args) throws SQLException {
		
		Connection conn=getConnection();
		
		if(conn != null) {
			System.out.println("Connected to database");
		}
		else {
			System.out.println("Failed to make connection!");
		}
	}

	private static Connection getConnection() {
		Connection conn = null;
		try {
			conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
