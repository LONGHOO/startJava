package com.shiyi.springboot.com.shiyi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-04-09 14:35
 * @Descrption
 **/
@Controller
public class con {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "list";
    }

}
