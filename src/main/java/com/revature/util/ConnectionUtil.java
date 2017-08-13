package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.OracleDriver;

public class ConnectionUtil {

	public static Connection getConnectionProp() throws IOException, SQLException{
		Properties  prop = new Properties();
		InputStream in = null;
		String url = ""; String user = ""; String password = "";
		try {
			in = new FileInputStream("connection.properties");
			prop.load(in);
		
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		}catch(FileNotFoundException e) {
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			user = "ers";
			password = "ers";
		}
		
		OracleDriver driver = new OracleDriver();
		DriverManager.registerDriver(driver);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		}catch(SQLException e) {
			url = "jdbc:oracle:think:@localhost:1521/orcl";
			conn = DriverManager.getConnection(url, user, password);
		}
		System.out.println(url); System.out.println(user); System.out.println(password);
		return conn;
		
	}
}
