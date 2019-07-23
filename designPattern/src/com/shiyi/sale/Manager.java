package com.shiyi.sale;

/**
 * @Author: 十一
 * @Date: 2019-05-02 18:14
 * @Descrption
 **/
public class Manager extends PriceHandler {
    @Override
    public void processPrice(Float price) {
        if(price <= 0.15){
            System.out.println("Manager processed the request "+price);
        }else{
            handler.processPrice(price);
        }
    }
}
