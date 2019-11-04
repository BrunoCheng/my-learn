package com.suixin.concurrent.L2线程并发工具类.L1Forkjoin分而治之.sum;

import java.util.Random;

/**
 * 创建一个长度为1亿的整形数组,每个整数在0~3亿之间
 */
public class MakeArray {
    //数组长度
    public static final int ARRAY_LENGTH  = 100000000;

    public static int[] makeArray() {

        //new一个随机数发生器
        Random r = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for(int i=0;i<ARRAY_LENGTH;i++){
            //用随机数填充数组,数值在3亿之间任意选择
            result[i] =  r.nextInt(ARRAY_LENGTH*3);
        }
        return result;

    }
}
