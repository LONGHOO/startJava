package com.shiyi.sale;

/**
 * @Author: 十一
 * @Date: 2019-05-02 18:12
 * @Descrption
 **/
public class Sales extends PriceHandler {
    @Override
    public void processPrice(Float price) {
        if(price <= 0.05f){
            System.out.println("Sales process the request "+ price);
        }else{
            handler.processPrice(price);
        }
    }
}