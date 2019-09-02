package com.suixin.concurrent.Java里面的线程.线程再认识.start方法和run方法的区别;

import com.suixin.commons.utils.SleepTools;

/**
 * @Description StartAndRun之前的区别
 * @Date 2019/7/15
 * @Created by acheng
 */
public class StartAndRun {
    public static class ThreadRun extends Thread{

        @Override
        public void run() {
            int i = 3;
            while(i>0){
                SleepTools.sleepByMS(1000);
                System.out.println("I am "+Thread.currentThread().getName()
                        +" and now the i="+i--);
            }
        }
    }

    private static class User {
        public void run() {

        }
    }

    public static void main(String[] args) {
        ThreadRun beCalled = new ThreadRun();
        beCalled.setName("BeCalled");
        //beCalled.setPriority(newPriority);
        beCalled.run();
        //执行run就和创建一个普通的User对象执行其run方法一样
        User user = new User();
        user.run();

        //使用start()开始
        beCalled.start();
        /**
         * 执行run方法,线程属于main线程
         * I am main and now the i=3
         * I am main and now the i=2
         * I am main and now the i=1
         * 执行start方法,线程属于启动线程
         * I am BeCalled and now the i=3
         * I am BeCalled and now the i=2
         * I am BeCalled and now the i=1
         */
    }
}
