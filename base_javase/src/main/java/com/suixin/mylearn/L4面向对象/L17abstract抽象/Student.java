package com.suixin.mylearn.L4面向对象.L17abstract抽象;

/**
 * @Description TODO
 * @Date 2019/10/24
 * @Created by acheng
 */
public class Student extends Person{
    @Override
    public void eat() {
        System.out.println("学生吃");
    }

    @Override
    public void work() {
        System.out.println("学生做");
    }
}
