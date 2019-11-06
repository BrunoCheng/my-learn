package com.suixin.concurrent.L4显示锁和AQS.L1显示锁.L1ReadWriteLock;

/**
 * @Description 2.商品的服务的接口
 * @Created by acheng
 */
public interface GoodsService {

    public GoodsInfo getNum();//获得商品的信息
    public void setNum(int number);//设置商品的数量
}
