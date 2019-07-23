package com.shiyi.factory;

import com.shiyi.sale.Manager;
import com.shiyi.sale.PriceHandler;
import com.shiyi.sale.Sales;
import com.shiyi.sale.SalesCEO;

/**
 * @Author: 十一
 * @Date: 2019-05-02 18:18
 * @Descrption
 **/
public class SalesFactory {

    public static PriceHandler getPriceHandler(){
        PriceHandler sales = new Sales();
        PriceHandler manager = new Manager();
        PriceHandler ceo = new SalesCEO();

        sales.setHandler(manager);
        manager.setHandler(ceo);
        return sales;
    }
}
