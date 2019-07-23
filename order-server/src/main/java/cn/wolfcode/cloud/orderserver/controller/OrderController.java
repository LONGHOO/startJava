package cn.wolfcode.cloud.orderserver.controller;

import cn.wolfcode.cloud.domain.Product;
import cn.wolfcode.cloud.orderserver.domain.Order;
import cn.wolfcode.cloud.orderserver.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 十一
 * @Date: 2019-07-09 18:36
 * @Descrption
 **/
@RestController
@Slf4j
@RefreshScope
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${env}")
    private String env;

    @RequestMapping("save")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "saveFail")
    public Order save(Long userId,int id) throws InterruptedException {
        log.info("进入order方法---环境为"+env);
        Order order = orderService.save(userId, id);

        return order;
    }

    public Order saveFail(Long userId,int id){
        log.info("进行降级---");
        String saveFail = redisTemplate.opsForValue().get("saveFail");
        if(StringUtils.isEmpty(saveFail)){
            redisTemplate.opsForValue().set("saveFail","saveFailValue",10, TimeUnit.SECONDS);
            new Thread(()->{
                System.out.println("正在发送邮箱通知运维人员");
            }).start();
        }else{
            log.info("saveFail..................");
        }
        return new Order();
    }
}
