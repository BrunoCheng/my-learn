package com.suixin.spring.anno.cp1.config;

import com.suixin.spring.anno.cp1.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 配置类
 * @Date 2019/7/24
 * @Created by acheng
 */
//配置类===配置文件
@Configuration
public class MainConfig {
    //要求获取person实例
    //给容易中注册一个bean,类型为返回值类型
    @Bean
    public Person person(){
        return new Person("acheng",20);
    }
}
