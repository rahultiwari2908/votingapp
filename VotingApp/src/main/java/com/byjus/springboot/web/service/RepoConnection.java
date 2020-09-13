package com.byjus.springboot.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class RepoConnection {
	private static Connection getRemoteConnection() {
	    if (System.getenv("RDS_HOSTNAME") != null) {
	      try {
	      Class.forName("org.postgresql.Driver");
	      String dbName = System.getenv("RDS_DB_NAME");
	      String userName = System.getenv("RDS_USERNAME");
	      String password = System.getenv("RDS_PASSWORD");
	      String hostname = System.getenv("RDS_HOSTNAME");
	      String port = System.getenv("RDS_PORT");
	      String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
	      //logger.trace("Getting remote connection with connection string from environment variables.");
	      Connection con = DriverManager.getConnection(jdbcUrl);
	     // logger.info("Remote connection successful.");
	      return con;
	    }
	    catch (ClassNotFoundException e) { 
	    	System.out.println(e);
	    	}
	    catch (SQLException e) { 
	    	System.out.println(e);
	    }
	    }
	    return null;
	  }
}
