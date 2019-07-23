package com.shiyi.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Author: 十一
 * @Date: 2019-04-29 14:51
 * @Descrption
 **/

@Aspect
public class LogAspects {

    @Pointcut("execution(* com.shiyi.service.impl.UserServiceImpl01.*(..))")
    public void point(){

    }
    @Before("point()")
    public void beforeLog(){
        System.out.println("before log...");
    }

    @After("point()")
    public void afterLog(){
        System.out.println("after log....");
    }

    @AfterReturning(value = "point()",returning = "result")
    public void returnLog(JoinPoint joinpoint, Object result){
        System.out.println("reutrn log ..."+joinpoint.getSignature().getName()+"'s return object is "+result);
    }

    @AfterThrowing(value = "point()",throwing = "e")
    public void returnExceptionLog(Exception e){
        System.out.println("return exception log ..."+e);
    }

    @Around("point()")
    public Object arroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("arround log before ..."+"args+"+joinPoint.getArgs());
        Object obj = joinPoint.proceed();
        System.out.println("arround log end ....");
        return obj;

    }
}
