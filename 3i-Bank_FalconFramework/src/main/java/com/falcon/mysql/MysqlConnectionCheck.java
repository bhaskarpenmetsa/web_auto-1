package com.falcon.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class MysqlConnectionCheck {
	 public static void main(String[] args) {
		 
		 Connection dbConnection = null;
		 
		 try {
		      String url = "jdbc:mysql://14.99.175.107:17633/icici_direct";
		      Properties info = new Properties();
		      info.put("user", "root");
		      info.put("password", "root");

		      dbConnection = DriverManager.getConnection(url, info);

		      if (dbConnection != null) {
		        System.out.println("Successfully connected to MySQL database test");
		      }

		    } catch (SQLException ex) {
		      System.out.println("An error occurred while connecting MySQL databse");
		      ex.printStackTrace();
		    }

		  }

}
	
