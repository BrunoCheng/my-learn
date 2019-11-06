package com.suixin.concurrent.L4显示锁和AQS.L1显示锁.L1ReadWriteLock;

import com.suixin.concurrent.L2线程并发工具类.L4Semaphore.SleepTools;

/**
 * @Description 3.用内置锁来实现商品服务接口
 * @Created by acheng
 */
public class UseSyn implements GoodsService {

    private GoodsInfo goodsInfo;

    public UseSyn(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized GoodsInfo getNum() {
        //模拟相关操作耗时
        SleepTools.ms(5);
        return this.goodsInfo;
    }

    @Override
    public synchronized void setNum(int number) {
        SleepTools.ms(5);
        goodsInfo.changeNumber(number);

    }
}
