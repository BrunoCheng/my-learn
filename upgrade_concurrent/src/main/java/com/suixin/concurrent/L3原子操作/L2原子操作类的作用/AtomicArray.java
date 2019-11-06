package com.suixin.concurrent.L3原子操作.L2原子操作类的作用;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Description 原子操作之基本数据类型演示
 * @Created by acheng
 */
public class AtomicArray {
    static int[] value = new int[] { 1, 2 };

    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));//3
        System.out.println(value[0]);//1

    }
}