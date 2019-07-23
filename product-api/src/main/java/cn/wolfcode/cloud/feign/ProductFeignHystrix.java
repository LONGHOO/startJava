package cn.wolfcode.cloud.feign;

import cn.wolfcode.cloud.domain.Product;
import org.springframework.stereotype.Component;

/**
 * @Author: 十一
 * @Date: 2019-07-10 18:32
 * @Descrption
 **/
@Component
public class ProductFeignHystrix implements ProductFeignApi {

    @Override
    public Product find(int id){
        Product product = new Product();
        product.setId(2L);
        product.setName("兜底数据");
        product.setPrice(8888);
        product.setStock(88);
        return product;
    }
}
