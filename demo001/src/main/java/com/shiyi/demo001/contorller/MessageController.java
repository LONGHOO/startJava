package com.shiyi.demo001.contorller;

import com.shiyi.demo001.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-06-14 14:27
 * @Descrption
 **/
@Controller
public class MessageController {

    @Autowired
    private Message message;

    @RequestMapping("message")
    @ResponseBody
    public String message(){
        return message.getAppkey()+message.getMessageUrl();
    }

}
