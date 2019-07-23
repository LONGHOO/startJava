package com.shiyi.controller;

import com.shiyi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-04-24 21:21
 * @Descrption
 **/
@Controller
public class PersonController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/person")
    @ResponseBody
    public String personHandle(){
        userService.service();
        return "hello person";
    }

    @RequestMapping("/list")
    public String listHandler(){
        return "list";
    }
}
