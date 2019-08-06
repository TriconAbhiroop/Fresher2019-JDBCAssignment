package com.tricon.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCStep3 {
	
	private static final String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
	private static final String user = "postgres";
	private static final String password = "admin";

	public static void main(String[] args) {
		
		try{
			Connection conn = DriverManager.getConnection(url, user, password);
			String updatePositionSql = "UPDATE employees SET position=? WHERE emp_id=?";
			PreparedStatement pstmt = conn.prepareStatement(updatePositionSql);
			
			pstmt.setString(1, "lead developer");
			pstmt.setInt(2, 1);
			
			int rowsAffected = pstmt.executeUpdate();
			System.out.println("Number of rows affected"+rowsAffected);
			
		}
		catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());	
		}
	}

}
