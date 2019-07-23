package com.shiyi.ioc;

/**
 * @Author: 十一
 * @Date: 2019-04-13 18:42
 * @Descrption
 **/
public class Dog {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public Dog(){
        System.out.println("constructor method be called!");
    }

    public void work(){
        System.out.println(name + "work");
    }

}
