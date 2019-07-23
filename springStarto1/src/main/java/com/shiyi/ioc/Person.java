package com.shiyi.ioc;

/**
 * @Author: 十一
 * @Date: 2019-04-13 18:34
 * @Descrption
 **/
public class Person {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void work(){
        System.out.println(name+" work");
    }
}
