package com.suixin.concurrent.启动和终止线程;

/**
 * @Description 中断Runnable类型的线程
 * @Date 2019/7/13
 * @Created by 62349
 */
public class EndRunnable {
    private static class UseRunnable implements Runnable{

        @Override
        public void run() {

            String threadName = Thread.currentThread().getName();
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println(threadName+" is run!");
            }
            System.out.println(threadName+" interrput flag is "
                    +Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable = new UseRunnable();
        Thread endThread = new Thread(useRunnable,"endThread");
        endThread.start();
        Thread.sleep(100);
        endThread.interrupt();
    }
}
