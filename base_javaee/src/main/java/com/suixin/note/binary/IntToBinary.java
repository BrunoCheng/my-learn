package com.suixin.note.binary;

import java.io.UnsupportedEncodingException;

/**
 * @Description 位运算规则
 * @Date 2016/10/19
 * @Created by acheng
 */
public class IntToBinary {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //测试数据
        int data = 4;
        //java 常见转2进制的方法
        //System.out.println("the 4 is "+Integer.toBinaryString(data));

        //与
        System.out.println("the 4   is "+Integer.toBinaryString(4));
        System.out.println("the 6   is "+Integer.toBinaryString(6));
        System.out.println("the 4&6 is "+Integer.toBinaryString(4&6));
        //the 4   is 100
        //the 6   is 110
        //the 4&6 is 100

        //或
        System.out.println("the 4   is "+Integer.toBinaryString(4));
        System.out.println("the 6   is "+Integer.toBinaryString(6));
        System.out.println("the 4|6 is "+Integer.toBinaryString(4|6));
        //the 4   is 100
        //the 6   is 110
        //the 4|6 is 110

        //非
        System.out.println("the ~4  is "+Integer.toBinaryString(~4));
        System.out.println("the ~-4 is "+Integer.toBinaryString(~-4));
        //the ~4  is 11111111111111111111111111111011
        //the ~-4 is 11

        //为异或
        System.out.println("the 4   is "+Integer.toBinaryString(4));
        System.out.println("the 6   is "+Integer.toBinaryString(6));
        System.out.println("the 4^6 is "+Integer.toBinaryString(4^6));
        //the 4   is 100
        //the 6   is 110
        //the 4^6 is 10

        //有符号左移<<、有符号右移>>、无符号右移>>>
        System.out.println("the 4   is "+Integer.toBinaryString(4));
        System.out.println("the 4<<2   is "+Integer.toBinaryString(4<<2));
        System.out.println("the -6     is "+Integer.toBinaryString(-6));
        System.out.println("the -6>>2  is "+Integer.toBinaryString(-6>>2));
        System.out.println(-6>>2);
        System.out.println(6>>2);
        System.out.println("the -6>>>2 is "+Integer.toBinaryString((-6)>>>2));
        //the 4      is 100
        //the 4<<2   is 10000
        //the -6     is 11111111111111111111111111111010
        //the -6>>2  is 11111111111111111111111111111110  代表值为负的-1取反
        //-2
        //1
        //the -6>>>2 is 111111111111111111111111111110

        //取模操作 a%(2^n) 等价于a&（2^n-1)
        System.out.println("the 915 % 16 is "+ (1917%16)+" or "+(1917&15));
        //注意位运算速度远快于这种乘除法或者取模
    }
}
