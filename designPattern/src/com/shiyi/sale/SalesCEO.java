package com.shiyi.sale;

/**
 * @Author: 十一
 * @Date: 2019-05-02 18:17
 * @Descrption
 **/
public class SalesCEO extends PriceHandler {

    @Override
    public  void processPrice(Float price) {
        if(price <= 0.3){
            System.out.println("sales ceo processed the request " + price);
        }else{
            System.out.println("the sales ceo denaid the request "+ price);
        }
    }
}
