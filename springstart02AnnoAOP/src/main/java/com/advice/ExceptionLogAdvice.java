package com.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: 十一
 * @Date: 2019-04-14 19:43
 * @Descrption
 **/
@Component
//声明为一个切面
@Aspect
public class ExceptionLogAdvice {

    //需要指定throwing的值
    @AfterThrowing(pointcut = "point()",throwing = "ex")
    public void showLog(Exception ex){
        System.out.println(ex.getMessage());
    }


    @Pointcut("execution(* com.shiyi.service.impl.*Service*.*(..))")
    public void point(){
    }
}
