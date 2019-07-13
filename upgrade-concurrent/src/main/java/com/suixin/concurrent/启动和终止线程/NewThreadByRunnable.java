package com.suixin.concurrent.启动和终止线程;

/**
 * @Description:开启线程的三种方法之一实现Runnable接口
 * @data:2016/7/13
 * @Auther:acheng
 */
public class NewThreadByRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("实现Runnable接口的线程");
    }
}
