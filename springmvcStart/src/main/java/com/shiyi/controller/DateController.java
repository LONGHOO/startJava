package com.shiyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-04-17 20:23
 * @Descrption
 **/
@Controller
public class DateController {

    @RequestMapping("/date")
    public String dateHander(ModelAndView view, Date date){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",date);
        view.addObject("msg", date);
        System.out.println(date.toLocaleString());
        return "index";
    }
}
