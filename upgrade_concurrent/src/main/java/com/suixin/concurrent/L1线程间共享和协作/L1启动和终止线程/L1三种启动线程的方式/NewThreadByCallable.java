package com.suixin.concurrent.L1线程间共享和协作.L1启动和终止线程.L1三种启动线程的方式;

import java.util.concurrent.Callable;

/**
 * @Description:开启线程的三种方法之一实现Runnable接口
 * @Auther:acheng
 */
public class NewThreadByCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "实现Callable接口的线程";
    }
}
