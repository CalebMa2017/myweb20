package com.lovecoding.day01.jdbc;

import org.junit.Test;

import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?useUnicode=true&characterEncoding=utf-8","root", "root");
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate("insert into t_user (name, sex) values('haha', 0)");
            if(rows > 0) {
                System.out.println("插入成功");
            }else {
                System.out.println("插入失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void testFn() {
        Connection conn = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?useUnicode=true&characterEncoding=utf-8", "root", "root");
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select id, name, sex from t_user");
            while(resultSet.next()) {
                System.out.println(resultSet.getInt("id") + ", " + resultSet.getString("name")+", " + resultSet.getInt("sex"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testFn1() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.createStatement();
            int rows = statement.executeUpdate("delete from t_user where id = 4");
            if(rows > 0) {
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseResource(connection, statement, null);
        }
    }
    @Test
    public void testFn2() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.createStatement();
            int rows = statement.executeUpdate("delete from t_user where id = 5");
            if(rows > 0) {
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseResource(connection, statement, null);
        }
    }

    @Test
    public void testFn3() {
        String username = "zhangsan' or 'hello";
        String password = "124";
        login(username, password);
    }

    private void login(String username, String password) {
        try {
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from t_user where username = ? and password = ?");
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                System.out.println("登录成功, 欢迎" + rs.getString("name"));
            }else {
                System.out.println("用户名或密码错误");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

