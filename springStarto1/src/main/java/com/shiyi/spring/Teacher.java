package com.shiyi.spring;

/**
 * @Author: 十一
 * @Date: 2019-04-13 13:16
 * @Descrption
 **/
public class Teacher {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void out(){
        System.out.println(this.name + "老师的out method");
    }
}
