package com.suixin.concurrent.启动和终止线程;

import com.suixin.commons.utils.SleepTools;

/**
 * @Description volatile关键字不能保证原子操作安全性
 * @Date 2019/7/14
 * @Created by acheng
 */
public class VolatileUnsafe {
    private static class VolatileVar implements Runnable {

        private volatile int a = 0;

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+":======"+a);
            a = a++;
            System.out.println(threadName+":======"+a);
            SleepTools.sleepByMS(200);
            a = a+1;
            System.out.println(threadName+":======"+a);
        }
    }

    public static void main(String[] args) {

        VolatileVar v = new VolatileVar();

        Thread t1 = new Thread(v);
        Thread t2 = new Thread(v);
        Thread t3 = new Thread(v);
        Thread t4 = new Thread(v);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
    /**
     * 某一次结果:
     * Thread-0:======0
     * Thread-1:======0
     * Thread-3:======0
     * Thread-2:======0
     * Thread-2:======2
     * Thread-1:======3
     * Thread-0:======2
     * Thread-3:======4
     * 可以看出a的值并没有按照1,2,3逐渐累加
     */
}
