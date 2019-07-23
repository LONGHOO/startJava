package com.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: 十一
 * @Date: 2019-04-14 19:42
 * @Descrption
 **/
@Component
@Aspect
public class AroundAdvice {

    //此参数仅仅适用于环绕通知，否则会报错
    @Around("point()")
    public void showAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("arroundAdvice before");
        //只有执行了proceed()方法，才会执行被拦截的方法
        pjp.proceed();
        System.out.println("arroundAdvice after");
    }

    @Pointcut("execution(* com.shiyi.service.impl.*Service*.*(..))")
    public void point(){}
}
