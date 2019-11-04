package com.suixin.mylearn.L4面向对象.L16final关键字;

/**
 * @Description final关键字测试题
 * @Date 2016/10/24
 * @Created by acheng
 */
public class FinalTest {
    public int addOne(final int x) {
        //return ++x;编译报错，++x是x=x+1，不符合final修饰规范
         return x + 1; //可以
    }

    public static void main(String[] args) {
        Other other = new Other();

    }
    public void addOne(final Other other){
        other.i++;//满足，Other不可变，其属下可变
        //other = new Other();编译报错
    }
}
class Other{
    public int i;
}