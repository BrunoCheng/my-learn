package com.suixin.concurrent.L3原子操作.L2原子操作类的作用;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 演示基本数据类
 * @Created by acheng
 */
public class UseAtomicInt {

    static AtomicInteger ai = new AtomicInteger(10);

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());//10--->11
        System.out.println(ai.incrementAndGet());//11--->12--->out
        System.out.println(ai.get());//12
    }
}