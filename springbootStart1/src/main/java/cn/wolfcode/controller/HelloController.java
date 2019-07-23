package cn.wolfcode.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 十一
 * @Date: 2019-06-09 19:57
 * @Descrption
 **/
@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping
    public String hello(Model model){
        model.addAttribute("hello", "hello world");
        return "hello";
    }
}
