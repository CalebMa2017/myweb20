package com.lovecoding.day02.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class C3p0Pool {
    private static ComboPooledDataSource dataSource;
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
    static {
        dataSource = new ComboPooledDataSource();
        /*try {
            dataSource.setDriverClass(resourceBundle.getString("jdbc.driver"));
            dataSource.setJdbcUrl(resourceBundle.getString("jdbc.url"));
            dataSource.setUser(resourceBundle.getString("mysql.username"));
            dataSource.setPassword(resourceBundle.getString("mysql.password"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }*/
    }
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static DataSource getDataSource() {
        return dataSource;
    }
}
