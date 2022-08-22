package com.lovecoding.day02.pool;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbcpPool {
    private static BasicDataSource dataSource;
    private static ResourceBundle rb = ResourceBundle.getBundle("jdbc");
    static {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(rb.getString("jdbc.driver"));
        dataSource.setUrl(rb.getString("jdbc.url"));
        dataSource.setUsername(rb.getString("mysql.username"));
        dataSource.setPassword(rb.getString("mysql.password"));
    }
    public static BasicDataSource getDataSource() {
        return dataSource;
    }
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
