package com.advice;

import org.aspectj.lang.JoinPoint;

/**
 * @Author: 十一
 * @Date: 2019-04-14 19:43
 * @Descrption
 **/
public class ExceptionLogAdvice {

    public void showLog(Exception ex){
        System.out.println(ex.getMessage());
    }
}
