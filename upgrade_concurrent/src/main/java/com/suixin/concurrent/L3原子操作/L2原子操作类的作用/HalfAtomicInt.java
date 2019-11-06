package com.suixin.concurrent.L3原子操作.L2原子操作类的作用;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 实现AtomicInteger的递增方法
 * @Created by acheng
 */
public class HalfAtomicInt {

    private AtomicInteger atomicI = new AtomicInteger(0);

    public void increament() {
        for (; ; ) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    public int getCount() {
        return atomicI.get();
    }

    public static void main(String[] args) {
        HalfAtomicInt ha = new HalfAtomicInt();
        ha.increament();
        System.out.println(ha.getCount());
    }

}