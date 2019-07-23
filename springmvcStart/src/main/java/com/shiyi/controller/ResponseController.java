package com.shiyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: 十一
 * @Date: 2019-04-17 20:25
 * @Descrption
 **/
@Controller
public class ResponseController {


    @RequestMapping("/resp")
    public ModelAndView getResp(User user){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/index.jsp");
        mv.addObject("msg", user);
        return mv;

    }

    @RequestMapping("/resp1")
    public String redirect(){
        return "redirect:/static/static.jsp";
    }

    @RequestMapping("/resp2")
    public String forward(){
        return "forward:/resp";
    }
}
