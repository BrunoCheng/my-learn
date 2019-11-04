package com.suixin.mylearn.L3数组;

import java.util.Arrays;

/**
 * @Description:一维数组内存测试
 * @data:2016/7/11
 * @Auther:acheng
 */
public class 数组初始化 {
    public static void main(String[] args) {
        int[] ids = new int[]{1001,1002,1003,1004};
        int[] a ={1,2,3};
        //动态初始化,先规定长度
        String[] names = new String[5];
        names[0] = "acheng";
        names[1] = "acheng";
        names[2] = "acheng";
        System.out.println(Arrays.toString(names));
        //[acheng, acheng, acheng, null, null]
        names[3] = "acheng";
        names[4] = "acheng";
        System.out.println(Arrays.toString(names));
        //[acheng, acheng, acheng, acheng, acheng]

        //静态初始化,规定了具体内容也相应规定了长度,所以说数组的长度一开始就是固定的
        String[] names2 = new String[]{"acheng","acheng","acheng","acheng","acheng"};
        System.out.println(Arrays.toString(names2));

        System.out.println(names==names2);//false
        System.out.println(names.equals(names2));//false ,显然数组本身没有重写==方法,还是比较的地址值
        System.out.println(Arrays.equals(names,names2));//true


        Object o1 = new String("acheng");
        Object o2 = "acheng";
        System.out.println(o1.hashCode());
        System.out.println(o2.hashCode());
        if (!(o1==null ? o2==null : o1.equals(o2))){
            System.out.println(false);//未执行
        }
    }
}
