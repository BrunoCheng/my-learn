package com.suixin.concurrent.Java里面的线程.线程再认识.Deamon线程;

import java.util.concurrent.ExecutionException;

/**
 * @Description 守护线程的使用和守护线程中的finally语句块
 * @Date 2016/7/14
 * @Created by acheng
 */
  class UseThread extends Thread {
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName()
                        + " I am extends Thread.");
            }
            System.out.println(Thread.currentThread().getName()
                    + " interrupt flag is " + isInterrupted());
        } finally {
            System.out.println("...........finally");
        }
    }
}

public class DaemonThread {

    public static void main(String[] args) throws InterruptedException,
            ExecutionException {
        UseThread useThread = new UseThread();
        //守护线程必须设置在start之前才有用
        useThread.setDaemon(true);
        useThread.start();
        Thread.sleep(500);
        //加上.interrupt()才会执行finally
        useThread.interrupt();
    }
}
