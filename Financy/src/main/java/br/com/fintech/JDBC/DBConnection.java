package br.com.fintech.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
	
	private static DBConnection instance;
	
	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	  
    public Connection tryConnection() {
    	
      Connection connection = null;
      
      try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
  
        connection = DriverManager.getConnection(
            "jdbc:oracle:thin:@URL",
            "User", "Password");
        
        System.out.println("Connection established !!!");
  
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      return connection;
    }

}
