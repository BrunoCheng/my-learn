package com.suixin.mylearn.L4面向对象.L14static关键字;

/**
 * @Description
 * @Date 2019/10/22
 * @Created by acheng
 */
class Father {
    static {
        System.out.println("11111111111");
    }

    {
        System.out.println("22222222222");
    }

    public Father() {
        System.out.println("33333333333");

    }

}

public class Son extends Father {
    static {
        System.out.println("44444444444");
    }

    {
        System.out.println("55555555555");
    }

    public Son() {
        System.out.println("66666666666");
    }


    public static void main(String[] args) { // 由父及子 静态先行
        //11111111111
        //44444444444
        System.out.println("77777777777");
        System.out.println("************************");
        Father son = new Son();
        //22222222222
        //33333333333
        //55555555555
        //66666666666
        System.out.println("************************");
        new Son();
        //22222222222
        //33333333333
        //55555555555
        //66666666666
        System.out.println("************************");
        new Father();
        //22222222222
        //33333333333
        //main方法只有new Father()
        //11111111111
        //44444444444
        //22222222222
        //33333333333

    }
}
