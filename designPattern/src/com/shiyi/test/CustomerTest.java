package com.shiyi.test;

import com.shiyi.factory.SalesFactory;
import com.shiyi.sale.PriceHandler;

import java.util.Random;

/**
 * @Author: 十一
 * @Date: 2019-05-02 18:21
 * @Descrption
 **/
public class CustomerTest {

    public static void main(String[] args){
        PriceHandler handler = SalesFactory.getPriceHandler();
        handler.processPrice(0.21195012f);
    }
}
