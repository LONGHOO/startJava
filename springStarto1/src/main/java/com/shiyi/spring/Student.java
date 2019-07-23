package com.shiyi.spring;

/**
 * @Author: 十一
 * @Date: 2019-04-13 12:37
 * @Descrption
 **/
public class Student {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void out(){
        System.out.println(this.name + "called out methdo");
    }
}
