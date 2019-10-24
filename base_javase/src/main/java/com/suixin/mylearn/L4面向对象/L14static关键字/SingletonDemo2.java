package com.suixin.mylearn.L4面向对象.L14static关键字;

/**
 * @Description 单例模式之懒汉式
 * @Date 2019/10/22
 * @Created by acheng
 */
public class SingletonDemo2 {

    private SingletonDemo2() {
    }

    private volatile static SingletonDemo2 singletonDemo2;

    public static SingletonDemo2 getInstance() {
        if (singletonDemo2 == null) {
            synchronized (SingletonDemo2.class) {
                if (singletonDemo2 == null) {
                    singletonDemo2 = new SingletonDemo2();
                }
            }
        }
        return singletonDemo2;
    }
}
