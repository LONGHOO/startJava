package com.shiyi.domain;

/**
 * @Author: 十一
 * @Date: 2019-05-02 19:38
 * @Descrption
 **/
public class Person implements ISave{


    private String name;

    private Integer age;

    public Person(){

    }

    public Person(String name){
        this.name = name;
    }

    public Person(String name,Integer age){
        this.name = name;
        this.age = age;
    }

    @Override
    public void save(){
        System.out.println("save method");
    }
}
