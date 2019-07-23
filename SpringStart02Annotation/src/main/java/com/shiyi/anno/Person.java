package com.shiyi.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: 十一
 * @Date: 2019-04-14 18:56
 * @Descrption
 **/
@Component
public class Person {

    @Value("虚竹")
    private String name;

    @Autowired
    private Dog dog;

    public void play(){
        System.out.println(this.name +"和他的"+this.dog.getName());
    }

}
