package com.suixin.spring.anno.cp1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description 原始Spring加载测试
 * @Date 2019/7/24
 * @Created by acheng
 */
public class MainTest1 {
    public static void main(String args[]){
        //把resourse文件夹下面beans.xml的类加载到IOC容器,相当于放到一个map里面
        ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
        //从容器中获取bean
        Person person = (Person) app.getBean("person");

        System.out.println(person);//Person [name=acheng, age=18]获取的是xml中的默认值
    }
}