package com.shiyi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @Author: 十一
 * @Date: 2019-04-27 16:09
 * @Descrption
 **/
@ComponentScan(value = "com.shiyi",excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
        value = Controller.class)} )
public class SpringConfig {

}
