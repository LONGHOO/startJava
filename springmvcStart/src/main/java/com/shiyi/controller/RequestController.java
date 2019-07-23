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
public class RequestController {

    @RequestMapping("/req")
    public ModelAndView requsetHandler(UserQueryObject queryObject){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/index.jsp");
        mv.addObject("msg", queryObject);
        return mv;
    }
}
