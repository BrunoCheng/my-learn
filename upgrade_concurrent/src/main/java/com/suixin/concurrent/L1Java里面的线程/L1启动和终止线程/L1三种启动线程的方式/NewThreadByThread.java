package com.suixin.concurrent.L1Java里面的线程.L1启动和终止线程.L1三种启动线程的方式;

/**
 * @Description:开启线程的三种方法之一继承Thread
 * @Auther:acheng
 */
public class NewThreadByThread extends Thread {
    /*继承扩展自Thread类
    * 创建一个新的类，该类继承 Thread 类，然后创建一个该类的实例。
    * 继承类必须重写 run() 方法，该方法是新线程的入口点。它也必须调用 start() 方法才能执行。
    * */
    public void run(){
        System.out.println("继承Thread开始的线程");
    }
}
