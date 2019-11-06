package com.suixin.concurrent.L3原子操作.L2原子操作类的作用;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description 演示带版本戳的原子操作类
 * @Created by acheng
 */
public class UseAtomicStampedReference {

    static AtomicStampedReference<String> asr =
            new AtomicStampedReference<>("Mark",0);


    public static void main(String[] args) throws InterruptedException {
        final int oldStamp = asr.getStamp();//那初始的版本号
        final String oldReferenc = asr.getReference();

        System.out.println(oldReferenc+"==========="+oldStamp);

        Thread rightStampThread = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()
                        +"当前变量值："+oldReferenc+"当前版本戳："+oldStamp+"-"
                        +asr.compareAndSet(oldReferenc, oldReferenc+"Java",
                        oldStamp, oldStamp+1));

            }

        });

        Thread errorStampThread = new Thread(new Runnable() {

            @Override
            public void run() {
                String reference = asr.getReference();
                System.out.println(Thread.currentThread().getName()
                        +"当前变量值："+reference+"当前版本戳："+asr.getStamp()+"-"
                        +asr.compareAndSet(reference, reference+"C",
                        oldStamp, oldStamp+1));

            }

        });

        rightStampThread.start();
        rightStampThread.join();
        errorStampThread.start();
        errorStampThread.join();
        System.out.println(asr.getReference()+"==========="+asr.getStamp());

    }
}