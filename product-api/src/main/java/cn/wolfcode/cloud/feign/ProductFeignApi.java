package cn.wolfcode.cloud.feign;

import cn.wolfcode.cloud.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: 十一
 * @Date: 2019-07-09 19:39
 * @Descrption
 **/
@FeignClient(name="PRODUCT-SERVER",fallback = ProductFeignHystrix.class)
public interface ProductFeignApi {

    @RequestMapping("/product/find")
    Product find(@RequestParam("id") int id) throws InterruptedException;
}
