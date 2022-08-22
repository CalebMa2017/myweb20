package com.lovecoding.day02;

import com.lovecoding.day02.bean.Order;
import com.lovecoding.day02.pool.C3p0Pool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class Example03 {
    public static void main(String[] args) {
        List<Order> orderList = getOrderList();
        for(Order o: orderList) {
            System.out.println(o);
        }
        System.out.println("--------------------------");
        System.out.println(getOrderAmount());
        System.out.println("--------------------------");
        System.out.println(getOrderByOname("34234326"));
    }

    /**
     * 获取订单列表
     * @return
     */
    public static List<Order> getOrderList() {
        QueryRunner qr = new QueryRunner(C3p0Pool.getDataSource());
        try {
            List<Order> orderList = qr.query("select o.oid, o.oname, o.create_time as createTime from t_order o",
                    new BeanListHandler<>(Order.class));
            return orderList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }
    /**
     * 获取订单数量
     */
    public static Long getOrderAmount() {
        QueryRunner qr = new QueryRunner(C3p0Pool.getDataSource());
        try {
            Long amount = (Long)qr.query("select count(*) from t_order", new ScalarHandler());
            return amount;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }
    /**
     * 根据订单名获取订单
     */
    public static Order getOrderByOname(String oname) {
        QueryRunner qr = new QueryRunner(C3p0Pool.getDataSource());
        Order order = null;
        try {
            order = qr.query("select o.oid, o.oname, o.create_time createTime from t_order o where o.oname = ?",
                    new BeanHandler<>(Order.class), oname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}
