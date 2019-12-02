package com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L2Volatile关键字;

/**
 * @Description 多线程情况下安全高效的单例
 * @Auther:acheng
 */
public class Singleton {
    private volatile static Singleton instance;
    private Singleton (){
    }

    public static Singleton getSingleton(){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
