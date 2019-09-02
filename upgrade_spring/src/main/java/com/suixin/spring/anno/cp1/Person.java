package com.suixin.spring.anno.cp1;

/**
 * @Description 一个普通javaBean实体类
 * @Date 2019/7/24
 * @Created by acheng
 */
public class Person {
    private String name;
    private Integer age;

    public Person(){
        super();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Person(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }
    public Integer getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
