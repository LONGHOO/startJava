package cn.wolfcode.cloud.porductserver.dao;

import cn.wolfcode.cloud.domain.Product;
import java.util.List;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author: 十一
 * @Date: 2019-07-09 17:17
 * @Descrption
 **/
@Component
public class ProductDao {

    private static  List<Product> list = new ArrayList<>();

    static{
        list.add(new Product(1L, "小米手机", 1999, 29));
        list.add(new Product(2L, "索尼手机", 2999, 2));
        list.add(new Product(3L, "oppo手机", 3999, 39));
        list.add(new Product(4L, "vivo手机", 4999, 59));
        list.add(new Product(5L, "苹果手机", 5599, 2));
        list.add(new Product(6L, "三星手机", 5999, 9));
        list.add(new Product(7L, "华为手机", 4999, 9));

    }



    public Product get(int id){
        return list.get(id);
    }
}
