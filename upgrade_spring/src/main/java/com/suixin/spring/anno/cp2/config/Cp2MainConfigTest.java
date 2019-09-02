package com.suixin.spring.anno.cp2.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description 测试@ComponentScan注解
 * @Date 2019/7/25
 * @Created by acheng
 */
public class Cp2MainConfigTest {
    @Test
    public void test02(){
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cp2MainConfig.class);
        //返回此工厂中定义的所有bean的名称。
        String[] names = app.getBeanDefinitionNames();
        for(String name:names){
            System.out.println(name);
        }
        /**
         * org.springframework.context.annotation.internalConfigurationAnnotationProcessor
         * org.springframework.context.annotation.internalAutowiredAnnotationProcessor
         * org.springframework.context.annotation.internalRequiredAnnotationProcessor
         * org.springframework.context.annotation.internalCommonAnnotationProcessor
         * org.springframework.context.event.internalEventListenerProcessor
         * org.springframework.context.event.internalEventListenerFactory
         * cp2MainConfig//默认会被扫描
         * orderController //只有Controller包下的注解类才被扫描到,并且如果里面有其他注解如@Bean的方法,也是会被扫描到的
         * 可以理解位类上面被扫描到,里面的bean方法也会被扫描到
         * person01//默认会被扫描到
         */
    }
    @Test
    public void test01(){
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cp2MainConfig.class);
        //返回此工厂中定义的所有bean的名称。
        String[] names = app.getBeanDefinitionNames();
        for(String name:names){
            System.out.println(name);
        }
        /**
         * org.springframework.context.annotation.internalConfigurationAnnotationProcessor
         * org.springframework.context.annotation.internalAutowiredAnnotationProcessor
         * org.springframework.context.annotation.internalRequiredAnnotationProcessor
         * org.springframework.context.annotation.internalCommonAnnotationProcessor
         * org.springframework.context.event.internalEventListenerProcessor
         * org.springframework.context.event.internalEventListenerFactory
         * 上面为默认加载器
         * cp2MainConfig
         * orderController
         * orderDao
         * orderService
         * person01
         * 可见只要是加了@的注解,无论是类cp2MainConfig,orderController,还是方法person01都会被扫描到
         */
    }
}
