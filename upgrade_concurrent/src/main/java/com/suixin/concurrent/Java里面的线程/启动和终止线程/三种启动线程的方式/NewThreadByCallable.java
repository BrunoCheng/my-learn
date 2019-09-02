package com.suixin.concurrent.Java里面的线程.启动和终止线程.三种启动线程的方式;

import java.util.concurrent.Callable;

/**
 * @Description:开启线程的三种方法之一实现Runnable接口
 * @data:2016/7/13
 * @Auther:acheng
 */
public class NewThreadByCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "实现Callable接口的线程";
    }
}
