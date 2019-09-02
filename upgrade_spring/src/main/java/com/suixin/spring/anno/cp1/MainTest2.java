package com.suixin.spring.anno.cp1;

import com.suixin.spring.anno.cp1.config.MainConfig;
import com.sun.deploy.util.ArrayUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @Description Spring加载测试, 使用注解
 * @Date 2019/7/24
 * @Created by acheng
 */
public class MainTest2 {
    public static void main(String args[]) {
        //使用xml配置bean
        //ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
        //Person person = (Person) app.getBean("person");
        //System.out.println(person);

        //启动main方法,将MainConfig加载到容器中来
        //核心容器类AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = (Person) anno.getBean("person");//注意名字首为小写
        System.out.println(person);//Person [name=acheng, age=20]
    }
}
//启动main方法,将MainConfig加载到容器中来
//核心容器类AnnotationConfigApplicationContext
//AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext(MainConfig.class);

//Person person = (Person) anno.getBean("person");//注意名字为小写
//System.out.println(person);

//获取Person在容器中的bean的id
//String[] str = anno.getBeanNamesForType(Person.class);
//System.out.println(Arrays.toString(str));//person
//实际上读取的是person()方法的名字
//如果要改就在@Bean("testPerson")
//System.out.println(Arrays.toString(str));//testPerson