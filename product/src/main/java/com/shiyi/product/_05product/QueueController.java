package com.shiyi.product._05product;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QueueController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RequestMapping("/queue")
    @ResponseBody
    public String sendMsg(String msg){
        rabbitTemplate.convertAndSend("","boot_queue",msg);
        return "发送成功";
    }
}