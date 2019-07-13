package com.suixin.mylearn.L2基础语法.L2_5流程控制;

import org.junit.Test;

/**
 * @Description:流程控制
 *  1.switch(表达式)中表达式的值必须是下述几种类型之一：byte，short， char，int，枚举 (jdk 5.0)，String (jdk 7.0)；
 *  2.case子句中的值必须是常量，不能是变量名或不确定的表达式值
 *  3.同一个switch语句，所有case子句中的常量值互不相同；
 *  4.break语句用来在执行完一个case分支后使程序跳出switch语句块；如果没有break，程序会顺序执行到switch结尾
 *  5.default子句是可任选的。同时，位置也是灵活的。当没有匹配的case时， 执行default
 * @data:2016/7/11
 * @Auther:acheng
 */
public class SwitchTest {
    //测试没有Break,当default在最后时
    @Test
    public void testNoBreak(){
        switch (7) {
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
            case 4:
                System.out.println(4);
            case 5:
                System.out.println(5);
            case 6:
                System.out.println(6);
            case 7:
                System.out.println(7);
            case 8:
                System.out.println(8);
            default:
                System.out.println("end");
        }//7 8 end
        //说明没有break不会停下来,而且会执行default
    }

    //测试没有Break,当default在中间时
    @Test
    public void testNoBreak2(){
        switch (7) {
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
            default:
                System.out.println("end");
            case 4:
                System.out.println(4);
            case 5:
                System.out.println(5);
            case 6:
                System.out.println(6);
            case 7:
                System.out.println(7);
            case 8:
                System.out.println(8);

        }//7 8
        //说明没有break不会停下来,而且自上而下执行,default只相当于一个特殊case值
    }

    //测试没有Break,当default在中间时且直接执行default
    @Test
    public void testNoBreak3(){
        switch (9) {
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
            default:
                System.out.println("end");
            case 4:
                System.out.println(4);
            case 5:
                System.out.println(5);
            case 6:
                System.out.println(6);
            case 7:
                System.out.println(7);
            case 8:
                System.out.println(8);

        }//end 4 5 6 7 8
        //default只相当于一个特殊case值
        //default很多时候也和case类似,只是对应都匹配的情况,并不是执行default就结束.
    }

}
