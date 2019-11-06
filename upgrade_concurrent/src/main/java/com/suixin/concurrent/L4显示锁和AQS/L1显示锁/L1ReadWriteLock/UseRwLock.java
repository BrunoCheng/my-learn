package com.suixin.concurrent.L4显示锁和AQS.L1显示锁.L1ReadWriteLock;

import com.suixin.concurrent.L2线程并发工具类.L4Semaphore.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description 使用显示锁完成读写操作
 * @Created by acheng
 */
public class UseRwLock implements GoodsService {

    private GoodsInfo goodsInfo;

    //显示锁需要加入lock和读锁和写锁来对应相关的读写操作
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock getLock = lock.readLock();//读锁
    private final Lock setLock = lock.writeLock();//写锁

    public UseRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public GoodsInfo getNum() {
        getLock.lock();
        try {
            SleepTools.ms(5);
            return this.goodsInfo;
        } finally {
            getLock.unlock();
        }

    }

    @Override
    public void setNum(int number) {
        setLock.lock();
        try {
            SleepTools.ms(5);
            goodsInfo.changeNumber(number);
        } finally {
            setLock.unlock();
        }
    }
}
