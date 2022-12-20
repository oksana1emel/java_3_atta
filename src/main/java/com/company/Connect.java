package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
    private static String url = "jdbc:postgresql://localhost/Lab_vsu_1";
    private static String username = "postgres";
    private static String password = "35728534oe";

    public static Statement statement() {
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn.createStatement();
        } catch (Exception ex) {
            System.out.println("Connection failed... " + ex);
            return null;
        }
    }

}
