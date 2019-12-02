package com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L1Synchronized内置锁;

import com.suixin.commons.utils.SleepTools;

/**
 * @Description 演示对象锁和类锁
 * @Auther:acheng
 */
public class SynClzAndInst {
    //使用类锁的线程
    private static class SynClass extends Thread{
        @Override
        public void run() {
            System.out.println("TestClass is running...");
            synClass();
        }
    }

    //使用对象锁的线程
    private static class InstanceSyn implements Runnable{
        private SynClzAndInst synClzAndInst;

        public InstanceSyn(SynClzAndInst synClzAndInst) {
            this.synClzAndInst = synClzAndInst;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running..."+synClzAndInst);
            synClzAndInst.instance();
        }
    }

    //使用对象锁的线程
    private static class Instance2Syn implements Runnable{
        private SynClzAndInst synClzAndInst;

        public Instance2Syn(SynClzAndInst synClzAndInst) {
            this.synClzAndInst = synClzAndInst;
        }
        @Override
        public void run() {
            System.out.println("TestInstance2 is running..."+synClzAndInst);
            synClzAndInst.instance2();
        }
    }

    //锁对象执行的方法
    private synchronized void instance(){
        SleepTools.second(3);
        System.out.println("synInstance is going..."+this.toString());
        SleepTools.second(3);
        System.out.println("synInstance ended "+this.toString());
    }

    //锁对象执行的方法
    private synchronized void instance2(){
        SleepTools.second(3);
        System.out.println("synInstance2 is going..."+this.toString());
        SleepTools.second(3);
        System.out.println("synInstance2 ended "+this.toString());
    }

    //类锁方法,拥有static synchronized.实际上,类锁是锁的是类的class对象
    private static synchronized void synClass(){
        SleepTools.second(1);
        System.out.println("synClass going...");
        SleepTools.second(1);
        System.out.println("synClass end");
    }

    public static void main(String[] args) {


        SynClzAndInst synClzAndInst = new SynClzAndInst();
        Thread t1 = new Thread(new InstanceSyn(synClzAndInst));

        SynClzAndInst synClzAndInst2 = new SynClzAndInst();
        Thread t2 = new Thread(new Instance2Syn(synClzAndInst2));

        //t1.start();
        //t2.start();

        /**
         * 结果为:
         TestInstance2 is running...com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L1Synchronized内置锁.SynClzAndInst@13d8848c
         TestInstance is running...com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L1Synchronized内置锁.SynClzAndInst@1ea98862
         synInstance is going...com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L1Synchronized内置锁.SynClzAndInst@1ea98862
         synInstance2 is going...com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L1Synchronized内置锁.SynClzAndInst@13d8848c
         synInstance2 ended com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L1Synchronized内置锁.SynClzAndInst@13d8848c
         synInstance ended com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L1Synchronized内置锁.SynClzAndInst@1ea98862
         我们可以发现,new两个对象,放到两个不同的线程去执行,可以一起执行互不干扰
         */
        SleepTools.second(2);


        SynClzAndInst synClzAndInst3 = new SynClzAndInst();
        Thread t3 = new Thread(new InstanceSyn(synClzAndInst3));
        Thread t4 = new Thread(new Instance2Syn(synClzAndInst3));

        //t3.start();
        //t4.start();

        /**
         * 结果为:
         * TestInstance is running...com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L1Synchronized内置锁.SynClzAndInst@36e493c8
         * TestInstance2 is running...com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L1Synchronized内置锁.SynClzAndInst@36e493c8
         * synInstance is going...com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L1Synchronized内置锁.SynClzAndInst@36e493c8
         * synInstance ended com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L1Synchronized内置锁.SynClzAndInst@36e493c8
         * synInstance2 is going...com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L1Synchronized内置锁.SynClzAndInst@36e493c8
         * synInstance2 ended com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L1Synchronized内置锁.SynClzAndInst@36e493c8
         * 可以看出:new同一个对象,放入不同的线程中执行,会逐个执行,直至最后一个执行结束
         */

        SynClass synClass1 = new SynClass();
        SynClass synClass2 = new SynClass();
        synClass1.start();
        synClass2.start();
        SleepTools.second(1);
        /**结果为
         * TestClass is running...
         * TestClass is running...
         * synClass going...
         * synClass end
         * synClass going...
         * synClass end
         * 类锁锁的是这个类的Class对象,虚拟机保证唯一,被锁住需要依次执行
         */

        //最后,类锁和对象锁也是可以同时进行的
    }
}
