package com.suixin.concurrent.L4显示锁和AQS.L1显示锁.L1ReadWriteLock;

/**
 * @Description 1.商品的实体类
 * @Created by acheng
 */
public class GoodsInfo {
    private final String name;
    private double totalMoney;//总销售额
    private int storeNumber;//库存数

    public GoodsInfo(String name, int totalMoney, int storeNumber) {
        this.name = name;
        this.totalMoney = totalMoney;
        this.storeNumber = storeNumber;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    //写操作：每没出去一件商品增加总销售额，降低库存数
    public void changeNumber(int sellNumber){
        this.totalMoney += sellNumber*25;
        this.storeNumber -= sellNumber;
    }
}