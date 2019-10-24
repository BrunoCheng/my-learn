package com.suixin.mylearn.L4面向对象.L14static关键字;

/**
 * @Description 多继承情况下子父类静态结构和代码块初始化顺序
 * @Date 2016/10/22
 * @Created by acheng
 */
class Root{
    static{
        System.out.println("Root的静态初始化块");
    }
    {
        System.out.println("Root的普通初始化块");
    }
    public Root(){
        System.out.println("Root的无参数的构造器");
    }
}
class Mid extends Root{
    static{
        System.out.println("Mid的静态初始化块");
    }
    {
        System.out.println("Mid的普通初始化块");
    }
    public Mid(String msg){
        //通过this调用同一类中重载的构造器
        this();
        System.out.println("Mid的带参数构造器，其参数值："
                + msg);
    }
    public Mid(){
        //不写super(),m默认去找父类无参构造器
        System.out.println("Mid的无参数的构造器");
    }

}
class Leaf extends Mid{
    static{
        System.out.println("Leaf的静态初始化块");
    }
    {
        System.out.println("Leaf的普通初始化块");
    }
    public Leaf(){
        //通过super调用父类中有一个字符串参数的构造器
        super("尚硅谷");
        System.out.println("Leaf的构造器");
    }
}
public class LeafTest{
    public static void main(String[] args){
        new Leaf();
        //执行逻辑如下
        //1.在main方法第一步，main方法属于LeafTest类，父类为object类，没有操作
        //2.执行到new Leaf();需要执行Leaf类的构造器，构造器里面调用父类Mid的super带参构造器
        //3.Mid的super带参构造器里面有this()，通过this调用同一类中重载的构造器，重载构造器在根据省略的无参构造器找到父类ROOT
        //4.Root构造器执行前需要创建类，此时先从Object类开始创建-》root》mid》leaf，依次执行了静态代码块
        //5.执行完成静态代码块之后回到步骤3，构造方法后于代码块执行，所以先执行root代码块，再执行root构造器
        //5.按照2-》3反向推进的属性执行mid和leaf的代码块和构造器

        //Root的静态初始化块
        //Mid的静态初始化块
        //Leaf的静态初始化块
        //Root的普通初始化块
        //Root的无参数的构造器
        //Mid的普通初始化块
        //Mid的无参数的构造器
        //Mid的带参数构造器，其参数值：尚硅谷
        //Leaf的普通初始化块
        //Leaf的构造器

        //new Leaf();再次执行则不再执行静态，其他非静态一样执行
    }
}