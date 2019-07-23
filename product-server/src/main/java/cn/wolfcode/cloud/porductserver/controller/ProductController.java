package cn.wolfcode.cloud.porductserver.controller;

import cn.wolfcode.cloud.domain.Product;
import cn.wolfcode.cloud.feign.ProductFeignApi;
import cn.wolfcode.cloud.porductserver.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 十一
 * @Date: 2019-07-09 17:14
 * @Descrption
 **/
@RestController
@Slf4j
@RefreshScope
public class ProductController implements ProductFeignApi {

    @Value("${server.port}")
    private String port;

    @Value("${env}")
    private String env;

    @Autowired
    private ProductService productService;

    @Override
    public Product find(int id) throws InterruptedException {
        log.info("访问product----------环境为"+env);
        Product product = productService.get(id);
        if(id % 2 == 0){
            TimeUnit.SECONDS.sleep(5);
        }
        Product result = new Product();
        BeanUtils.copyProperties(product,result);
        result.setName(result.getName()+" 来自 "+port);
        return result;
    }


}
