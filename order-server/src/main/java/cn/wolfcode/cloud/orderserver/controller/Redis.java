package cn.wolfcode.cloud.orderserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 十一
 * @Date: 2019-07-11 14:14
 * @Descrption
 **/
@RestController
public class Redis {

    @Autowired
    private StringRedisTemplate template;

    @RequestMapping("/redis")
    public String redis(){
        String name = template.opsForValue().get("name");
        return name;
    }
}
