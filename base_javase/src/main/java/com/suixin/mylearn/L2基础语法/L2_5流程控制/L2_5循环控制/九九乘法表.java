package com.suixin.mylearn.L2基础语法.L2_5流程控制.L2_5循环控制;

/**
 * @Description:打印九九乘法表
 * @data:2019/7/11
 * @Auther:acheng
 */
public class 九九乘法表 {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j+"*"+i+"="+(j*i)+"\t");
            }
            System.out.println();
        }
    }
}
