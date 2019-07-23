package com.shiyi.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-04-17 20:24
 * @Descrption
 **/
@ControllerAdvice
public class DateControllerAdvice {

    @InitBinder
    public void bind(WebDataBinder dataBinder){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor cde = new CustomDateEditor(sdf,true);
        dataBinder.registerCustomEditor(Date.class,cde);
    }
}
