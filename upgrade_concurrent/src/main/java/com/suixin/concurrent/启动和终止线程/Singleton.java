package com.suixin.concurrent.启动和终止线程;

/**
 * @Description 多线程情况下安全高效的单例
 * @Date 2019/7/15
 * @Created by acheng
 */
public class Singleton {

    private volatile static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }else {
                    return instance;
                }
            }
        }
        return instance;
    }
}
