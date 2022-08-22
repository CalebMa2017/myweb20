package com.lovecoding.day02.pool;

import com.lovecoding.day02.bean.Product;
import org.junit.Test;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Example01 {
    public static void main(String[] args) {
        List<Product> productList = getProductList();
        for (Product p : productList) {
            System.out.println(p);
        }
    }

    public static List<Product> getProductList() {
        try {
            MyConnectionPool pool = new MyConnectionPool();
            Connection connection = pool.getConnection();
            String sql = "select p.pid, p.pname, p.price, p.cid, p.create_time createTime from product p";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setPid(resultSet.getLong("pid"));
                product.setPname(resultSet.getString("pname"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCid(resultSet.getInt("cid"));
                product.setCreateTime(resultSet.getString("createTime"));
                productList.add(product);
            }
            pool.releaseConnection(connection);
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testFn() {
        try {
            Connection connection = C3p0Pool.getConnection();
            String sql = "insert into product (pname, price, cid, create_time) values(?, ?,?,? )";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "华为手机");
            preparedStatement.setDouble(2, 8000.0);
            preparedStatement.setInt(3, 1);
            preparedStatement.setString(4, "2022-08-21");
            int rows = preparedStatement.executeUpdate();
            if(rows > 0) {
                System.out.println("更新成功");
            }else {
                System.out.println("更新失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
