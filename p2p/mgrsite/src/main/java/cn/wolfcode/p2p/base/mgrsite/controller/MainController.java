package cn.wolfcode.p2p.base.mgrsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 十一
 * @Date: 2019-06-15 19:53
 * @Descrption
 **/
@Controller
public class MainController {

    @RequestMapping("main")
    public String mainPage(){
        return "main";
    }
}
