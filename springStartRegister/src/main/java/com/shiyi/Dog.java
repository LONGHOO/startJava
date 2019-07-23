package com.shiyi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: 十一
 * @Date: 2019-04-14 18:51
 * @Descrption
 **/
@Component
public class Dog {

    @Value("来福")
    private String name;

    public void word(){
        System.out.println(this.name+"在玩儿");
    }
}
