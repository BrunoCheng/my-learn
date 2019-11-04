package com.suixin.mylearn.L3数组;

import java.util.Arrays;

/**
 * @Description Arrays提供的数据工具类
 * @Date 2019/11/1
 * @Created by acheng
 */
public class ArraysUtils {
    public static void main(String[] args) {
        //1.boolean equals(int[] a,int[] b):判断两个数组是否相等。
        int[] arr1 = new int[]{1,2,3,4};

        int[] arr2 = new int[]{1,3,2,4};
        boolean isEquals = Arrays.equals(arr1, arr2);
        System.out.println(isEquals);//false,数组是有顺序阿
        int[] arr3 = new int[]{1,2,3,4};//true

        System.out.println(arr1.toString());
        System.out.println(arr3.toString());
        //[I@330bedb4
        //[I@2503dbd3说明两个地址值是不一样的

        int[] arr4 = new int[0];
        int[] arr5 = new int[0];
        boolean isEquals2 = Arrays.equals(arr4, arr5);//false
        //boolean isEquals2 = arr4.equals(arr3);//false
        System.out.println(isEquals);//动态初始化同长度都为null,两个比较数组只要有一个为null,都为false

        //2.String toString(int[] a):输出数组信息。
        System.out.println(Arrays.toString(arr1));//[1, 2, 3, 4]


        //3.void fill(int[] a,int val):将指定值填充到数组之中。
        Arrays.fill(arr1,10);
        System.out.println(Arrays.toString(arr1));//[10, 10, 10, 10]


        //4.void sort(int[] a):对数组进行排序。
        //底层使用的是DualPivotQuicksort.双轴快排
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        //5.int binarySearch(int[] a,int key)二分查找
        int[] arr6 = new int[]{-98,-34,2,34,54,66,79,105,210,333};
        int index = Arrays.binarySearch(arr3, 210);
        if(index >= 0){
            System.out.println(index);
        }else{
            System.out.println("未找到");//未找到
        }
    }
}
