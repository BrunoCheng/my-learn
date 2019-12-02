package com.suixin.concurrent.L7并发容器.L3阻塞队列.Demo;

/**
 * @Description 订单实体类
 * @Created by acheng
 */
public class Order {
    private final String orderNo;//订单的编号
    private final double orderMoney;//订单的金额

    public Order(String orderNo, double orderMoney) {
        super();
        this.orderNo = orderNo;
        this.orderMoney = orderMoney;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public double getOrderMoney() {
        return orderMoney;
    }
}