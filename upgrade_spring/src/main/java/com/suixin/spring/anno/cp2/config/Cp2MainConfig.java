package com.suixin.spring.anno.cp2.config;

import com.suixin.spring.anno.cp1.Person;
import com.suixin.spring.anno.cp2.controller.OrderController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;


@Configuration
@ComponentScan(value = "com.suixin.spring.anno.cp2",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {OrderController.class})},
        useDefaultFilters = true)
//useDefaultFilters默认为true,即不使用过滤.
//当使用excludeFilters时,useDefaultFilters为true生效
//当使用includeFilters时,useDefaultFilters为false生效
public class Cp2MainConfig {
    @Bean
    public Person person01() {
        return new Person("acheng", 25);
    }
}
