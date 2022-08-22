package com.lovecoding.day02.pool;

import com.lovecoding.day02.bean.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Example02 {
    public static void main(String[] args) {
//        delete();
        System.out.println(update("vivo手机"));
    }
    public static int delete() {
        try {
            Connection connection = C3p0Pool.getConnection();
            String sql = "delete from product where pid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, 5);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int update(String name) {
        try {
            Connection conn = DbcpPool.getConnection();
            String sql = "update product set pname = ? where pid = 6";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
