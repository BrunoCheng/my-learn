package com.suixin.concurrent.启动和终止线程.waitandnotify;

/**
 * @Description 测试Notify和Wait方法,等待和通知机制
 * @Date 2019/7/15
 * @Created by acheng
 */
public class TestNotifyAndWait {

    static Express express = new Express(0,Express.CITY);

    /*检查里程数变化的线程,不满足条件，线程一直等待*/
    private static class CheckKm extends Thread{
        @Override
        public void run() {
            express.waitKm();
        }
    }

    /*检查地点变化的线程,不满足条件，线程一直等待*/
    private static class CheckSite extends Thread{
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<3;i++){//三个线程检查地点变化
            new CheckSite().start();
        }
        for(int i=0;i<3;i++){//三个线程检查里程数的变化
            new CheckKm().start();
        }
        //主线程休眠,让出使用权
        Thread.sleep(1000);
        //此时上面三个地点检查和里程检查的都在wait()停止住了
        //快递地点变化,changeKm()里的notifyAll通知了所有的等待的线程
        //所有的wait()的线程都变成了就绪状态
        //此时waitKm的循环条件满足了,向下执行,而waitSite还是在循环里面,又进入了wait(),所以现在程序还是在运行.

        //这里如果changeKm()里面为notify方法,那么久只会通知一次,不一定就是waitKm的接受到.
        express.changeKm();

        /**
         * check km thread[17] is be notifed.
         * the km is101,I will change db.
         * check km thread[16] is be notifed.
         * the km is101,I will change db.
         * check km thread[15] is be notifed.
         * the km is101,I will change db.
         * check site thread[14] is be notifed.
         * check site thread[13] is be notifed.
         * check site thread[12] is be notifed.
         */
    }
}
