package cn.wolfcode.cloud.orderserver.service;

import cn.wolfcode.cloud.domain.Product;
import cn.wolfcode.cloud.feign.ProductFeignApi;
import cn.wolfcode.cloud.orderserver.domain.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: 十一
 * @Date: 2019-07-09 19:10
 * @Descrption
 **/
@Service
@Slf4j
public class OrderService {

    @Autowired
    private ProductFeignApi productFeignApi;

    public Order save(Long userId,int id) throws InterruptedException {
        Product product = productFeignApi.find(id);
        log.info("result------------>"+product.toString());
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setOrderNo(UUID.randomUUID().toString().replace("-",""));
        order.setProductName(product.getName());
        order.setProductPrice(product.getPrice());
        order.setUserId(1L);
        System.out.println(order);
        return order;
    }
}
