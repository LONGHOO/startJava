package com.shiyi.anno;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: 十一
 * @Date: 2019-04-14 18:55
 * @Descrption
 **/
@Component
public class Dog {

    @Value("来福")
    private String name;

    public String getName() {
        return name;
    }

    public void work(){
        System.out.println(this.name + "在玩儿");
    }

}
