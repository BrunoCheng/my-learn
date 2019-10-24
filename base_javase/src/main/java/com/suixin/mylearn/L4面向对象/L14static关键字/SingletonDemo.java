package com.suixin.mylearn.L4面向对象.L14static关键字;

/**
 * @Description 单例模式之饿汉式，
 * @Date 2016/10/22
 * @Created by acheng
 */
public class SingletonDemo {
    // 1.私有化构造器
    private SingletonDemo(){}

    // 2.内部提供一个当前类的实例
    // 4.此实例也必须静态
    private static SingletonDemo singletonDemo =new SingletonDemo();
    // 3.提供公共的静态的方法，返回当前类的对象
    public static SingletonDemo getInstance(){
        return singletonDemo;
    }
}
