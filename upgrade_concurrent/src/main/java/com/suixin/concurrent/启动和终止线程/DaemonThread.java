package com.suixin.concurrent.启动和终止线程;

import java.util.concurrent.ExecutionException;

/**
 * @Description 守护线程的使用和守护线程中的finally语句块
 * @Date 2019/7/14
 * @Created by acheng
 */
public class DaemonThread {
    private static class UseThread extends Thread {
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
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

    public static void main(String[] args) throws InterruptedException,
            ExecutionException {
        UseThread useThread = new UseThread();
        //守护线程必须设置在start之前才有用
        useThread.setDaemon(true);
        useThread.start();
        Thread.sleep(5);
        useThread.interrupt();
    }
}
