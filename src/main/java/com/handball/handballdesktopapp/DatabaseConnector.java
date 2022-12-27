package com.handball.handballdesktopapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnector {
    public Connection databaseLink;

    public Connection getConnection() throws SQLException {
        String databaseName="handball";
        String databaseUser="root";
        String databasePassword="password1234";
        String url="jdbc:mysql://localhost:3306/"+databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
            System.out.println(databaseName);
        } catch(Exception e){
                e.printStackTrace();
        }
        return databaseLink;
    };
}
