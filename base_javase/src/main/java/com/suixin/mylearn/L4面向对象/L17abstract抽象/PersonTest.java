package com.suixin.mylearn.L4面向对象.L17abstract抽象;

/**
 * @Description 抽象类的匿名实现类，匿名最大的好处就是简化，多事用在只使用一次的位置
 * @Date 2019/10/24
 * @Created by acheng
 */
public class PersonTest {
    public static void main(String[] args) {
        // 常规1
        method2(new Student());//匿名对象，new Student没有对象名字
        System.out.println("******************");


        // 常规2
        //method方法形参是抽象类Person，不能够被实例化，需要Person子类
        Student s = new Student();
        method(s);//有名的类Student，有名的对象s
        System.out.println("******************");


        // 匿名1
        // 使用非抽象匿名对象，传入的是new Student()这个类的虚拟对象，对象没有名字
        method(new Student());
        System.out.println("******************");


        // 匿名2，创建了一个对象名为p的Person的子类，多态赋值给Person接收
        Person p = new Person(){
            @Override
            public void eat() {
                System.out.println("大人吃");
            }

            @Override
            public void work() {
                System.out.println("大人做");
            }
        };
        method(p);
        System.out.println("******************");

        //匿名3
        //使用抽象匿名对象做参数
        //此时其实是代码Person person = new PersonDemo();
        //这个demo没有实际重写，所以需要我们直接重写构造方法，然后根据多态性Person类接收
        method(new Person() {
            @Override
            public void eat() {
                System.out.println("大人吃");
            }

            @Override
            public void work() {
                System.out.println("大人做");
            }
        });
    }
    public static void method(Person person){
        person.eat();
        person.work();
    }
    public static void method2(Person student){
        student.eat();
        student.work();
    }
}
