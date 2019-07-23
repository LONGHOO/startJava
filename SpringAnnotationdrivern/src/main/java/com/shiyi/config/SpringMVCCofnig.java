package com.shiyi.config;

import com.shiyi.interceptor.MyInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 十一
 * @Date: 2019-04-27 16:12
 * @Descrption
 **/
@ComponentScan(value = "com.shiyi",includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
        value = Controller.class)},useDefaultFilters = false)

public class SpringMVCCofnig{



}
