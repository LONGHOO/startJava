package com.shiyi.product._06pubProduct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-07-06 22:11
 * @Descrption topic
 **/
@Controller
public class Pubproduct {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RequestMapping("/direct"
    @ResponseBody
    public String sendMsg(String msg){
        rabbitTemplate.convertAndSend("boot_pubsub",msg,"xiaoxi neirong");
        return "发送成功";
    }
}
