package com.bank.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtility {
	/*
	 * public static void main(String[] args) throws Exception {
	 * Class.forName("oracle.jdbc.driver.OracleDriver"); // loaded jdbc driver
	 * Connection con =
	 * DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system",
	 * "root"); Statement stmt = con.createStatement();
	 * 
	 * ResultSet rs = stmt.executeQuery("select * from account");
	 * 
	 * while (rs.next()) { System.out.println(rs.getString(1) + " " + rs.getInt(2) +
	 * " " + rs.getInt(3)); } con.close();
	 * 
	 * }
	 */

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // loaded jdbc driver
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;

	}

}
