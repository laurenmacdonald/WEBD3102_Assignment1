package com.example.webd3102_assignment1.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    public static Connection getConnection() throws SQLException {
        //sql is schema name (table)
        String url = "jdbc:mysql://localhost:3306/to_do_app";
        String uname = "root";
        String pass = "";
        return DriverManager.getConnection(url, uname, pass);
    }

}