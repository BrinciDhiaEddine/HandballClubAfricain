package com.handball.handballdesktopapp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2DatabaseConnector {
    public Connection databaseLink;

    public Connection getConnection() throws SQLException {
         String jdbcURL = "jdbc:h2:~/test";
         String jdbcUsername = "sa";
         String jdbcPassword = "";

        try {
            databaseLink = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
            System.out.println("you are connected");
        } catch(Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    };
}
