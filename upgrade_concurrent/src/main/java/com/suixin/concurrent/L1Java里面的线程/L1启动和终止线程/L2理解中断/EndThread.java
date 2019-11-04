package com.suixin.concurrent.L1Java里面的线程.L1启动和终止线程.L2理解中断;

/**
 * @Description 如何安全的中断线程
 * @Auther:acheng
 */
public class EndThread {
    private static class UseThread extends Thread {
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            //判定当前不是要中断的线程

            //这里以功能:不断打印线程名为例
            String threadName = Thread.currentThread().getName();
            while (!isInterrupted()) {
                System.out.println(threadName + "run");
            }
            System.out.println(threadName + " interrput flag is "
                    + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread endThread = new UseThread("endThread");
        //开始线程
        endThread.start();
        //休眠一下
        Thread.sleep(100);

        //标记中断,将标记中断位改为true
        endThread.interrupt();
    }
}
