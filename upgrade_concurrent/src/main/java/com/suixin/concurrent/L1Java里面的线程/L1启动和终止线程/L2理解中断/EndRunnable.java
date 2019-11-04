package com.suixin.concurrent.L1Java里面的线程.L1启动和终止线程.L2理解中断;

/**
 * @Description 中断Runnable类型的线程
 * @Auther:acheng
 */
public class EndRunnable {
    private static class UseRunnable implements Runnable{
        int a = 0;
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println(threadName+" is run "+"a:"+a);
                a++;
            }
            System.out.println(threadName+" interrput flag is "
                    +Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable = new UseRunnable();
        Thread endThread = new Thread(useRunnable);
        endThread.start();
        Thread.sleep(100);
        endThread.interrupt();
    }
}
