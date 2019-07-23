package com.shiyi.sale;

/**
 * @Author: 十一
 * @Date: 2019-05-02 18:10
 * @Descrption
 **/
public abstract class PriceHandler {

    protected PriceHandler handler;

    public void setHandler(PriceHandler handler) {
        this.handler = handler;
    }

    public abstract void processPrice(Float price);
}
