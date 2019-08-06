package com.tricon.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tricon.jdbc.model.Employee;

public class JDBCStep4 {
	
	private static final String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
	private static final String user = "postgres";
	private static final String password = "admin";

	public static void main(String[] args) {
		
		try{
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt=conn.createStatement();
			/*
			 * String tableSql = "CREATE TABLE IF NOT EXISTS employees" +
			 * "(emp_id SERIAL PRIMARY KEY , name varchar(30)," +
			 * "position varchar(30), salary bigint)"; stmt.execute(tableSql);
			 */
			
			String insertSql = "INSERT INTO employees(name, position, salary)"
					  + " VALUES('wick', 'developer', 2000)";
			int noOfInsert=stmt.executeUpdate(insertSql);
			
			System.out.println("Number of inserts "+ noOfInsert);
			
			String selectSql = "SELECT * FROM employees";
			ResultSet resultSet = stmt.executeQuery(selectSql);
			
			List<Employee> employees = new ArrayList<>();
	         
			while (resultSet.next()) {
			    Employee emp = new Employee();
			    emp.setId(resultSet.getInt("emp_id"));
			    emp.setName(resultSet.getString("name"));
			    emp.setPosition(resultSet.getString("position"));
			    emp.setSalary(resultSet.getDouble("salary"));
			    employees.add(emp);
			}
			
			employees.forEach(e -> System.out.println(e.getName()+" "+e.getPosition()));
			
		}
		catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());	
		}
	}

}
