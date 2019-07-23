package com.shiyi.mybatis.domain;

import lombok.Getter;

/**
 * @Author: 十一
 * @Date: 2019-04-10 23:52
 * @Descrption
 **/
public class Father {
    private String name;

    private String getFatherName(){
        return this.name;
    }

}
