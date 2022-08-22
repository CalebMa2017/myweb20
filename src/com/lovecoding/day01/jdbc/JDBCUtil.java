package com.lovecoding.day01.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCUtil {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    static {
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        driver = rb.getString("jdbc.driver");
        url = rb.getString("jdbc.url");
        username = rb.getString("mysql.username");
        password = rb.getString("mysql.password");
    }
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void releaseResource(Connection conn, Statement stmt, ResultSet rs) {
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
