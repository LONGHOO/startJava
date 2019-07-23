package cn.wolfcode.test.testspringstarter.controller;

import cn.wolfcode.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-06-11 15:26
 * @Descrption
 **/
@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        helloService.sayHello("hello");
        return "hello";
    }
}
