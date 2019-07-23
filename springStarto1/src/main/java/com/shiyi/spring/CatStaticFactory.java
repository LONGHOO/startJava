package com.shiyi.spring;

/**
 * @Author: 十一
 * @Date: 2019-04-13 15:36
 * @Descrption
 **/
public class CatStaticFactory {

    private CatStaticFactory(){}

    public static Cat getInstance(){
        return new Cat();
    }
}
