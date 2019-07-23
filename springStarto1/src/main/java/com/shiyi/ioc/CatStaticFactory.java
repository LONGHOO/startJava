package com.shiyi.ioc;

/**
 * @Author: 十一
 * @Date: 2019-04-13 18:42
 * @Descrption
 **/
public class CatStaticFactory {

    private CatStaticFactory(){}

    public static Cat2 getInstance(){
        return new Cat2();
    }

}
