package com.suixin.concurrent.L4显示锁和AQS.L1显示锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 使用显示锁的范式
 * @Created by acheng
 */
public class LockDemo {

    private Lock lock  = new ReentrantLock();//ReentrantLock可重入锁的实现
    private int count;

    public void increament() {
        lock.lock();
        try {
            count++;
        }finally {
            lock.unlock();
        }
    }

    public synchronized void incr2() {
        count++;//synchronize明显比lock简洁
        incr2();//可重入锁
    }

    public synchronized void test3() {
        incr2();
    }

}