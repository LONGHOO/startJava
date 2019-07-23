package com.longhoo.cglib;

/**
 * @Author: 十一
 * @Date: 2019-03-05 14:32
 * @Descrption
 **/
public class HelloWorld {


    private String name;

    public HelloWorld(String name){
        this.name = name;
    }

    public HelloWorld(){}

    public String sayHello(){
        return "hello world";
//        System.out.println("hello world");
    }

}
