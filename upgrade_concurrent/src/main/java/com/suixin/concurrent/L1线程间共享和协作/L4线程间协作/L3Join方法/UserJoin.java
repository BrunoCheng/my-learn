package com.suixin.concurrent.L1线程间共享和协作.L4线程间协作.L3Join方法;

import com.suixin.commons.utils.SleepTools;

/**
 * @Description 使用Join方法
 * @Created by acheng
 */
class UseJoin {
    static class JumpQueue implements Runnable {

        private Thread thread;//用来插队的线程

        public JumpQueue(Thread thread) {
            this.thread = thread;
        }

        public void run() {
            try {
                System.out.println(thread.getName() + " will be join before "
                        + Thread.currentThread().getName());
                thread.join();//插队，相当于交出当前线程的执行权
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminted（终止）.");
        }
    }

    public static void main(String[] args) throws Exception {
        Thread previous = Thread.currentThread();//现在是主线程main
        for (int i = 0; i < 10; i++) {
            //当i=0,previous 是主线程，当i=1;previous是i=0这个线程，循环插队，意味着I=9时，必须等到所有插队线程完成之后才可以完成。
            Thread thread =
                    new Thread(new JumpQueue(previous), String.valueOf(i));
            System.out.println(previous.getName() + " jump a queue the thread:"
                    + thread.getName());
            thread.start();
            previous = thread;
        }
        SleepTools.second(4);//让主线程休眠2秒，可以明显感受到线程都在等待main线程执行
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }
}