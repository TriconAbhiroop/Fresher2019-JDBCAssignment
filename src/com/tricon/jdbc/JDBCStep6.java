package com.tricon.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCStep6 {
	
	private static final String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
	private static final String user = "postgres";
	private static final String password = "admin";

	public static void main(String[] args) {
		
		try{
			// Step 1 getting connection 
			Connection conn = DriverManager.getConnection(url, user, password);
			
			//Step 2 create Prepared statement
			String updatePositionSql = "UPDATE employees SET position=? WHERE emp_id=?";
			PreparedStatement pstmt = conn.prepareStatement(updatePositionSql);
			pstmt.setString(1, "Senior lead dev");
			pstmt.setInt(2, 1);
			
			
			String updateSalarySql = "UPDATE employees SET salary=? WHERE emp_id=?";
			PreparedStatement pstmt2 = conn.prepareStatement(updateSalarySql);
			pstmt2.setDouble(1, 6000);
			pstmt2.setInt(2, 1);
			
			boolean autoCommit= conn.getAutoCommit();
			
			try {
				conn.setAutoCommit(false);
				
				//Step 3 Execute the statement
				pstmt.executeUpdate();
				pstmt2.executeUpdate();
				conn.commit();
				
				System.out.println("Updated Successfully");
			}
			catch (SQLException e) {
				conn.rollback();
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());	
				
			}
			finally {
				//Step 4 close statements
				pstmt.close();
				pstmt2.close();
				
				//Step 5 close Connection
				conn.close();
			}
			
		}
		catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());	
		}
	}

}
