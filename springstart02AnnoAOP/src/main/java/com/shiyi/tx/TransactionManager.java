package com.shiyi.tx;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: 十一
 * @Date: 2019-04-14 19:40
 * @Descrption
 **/
@Component
@Aspect
public class TransactionManager {

    @Before("pointcut()")
    public void begin(){
        System.out.println("begin");
    }

    @After("pointcut()")
    public void commit(){
        System.out.println("commit");
    }

    @AfterThrowing("pointcut()")
    public void rollBack(){
        System.out.println("rollback");
    }

    @Pointcut("execution(* com.shiyi.service.impl.*Service*.*(..))")
    public void pointcut(){}
}
