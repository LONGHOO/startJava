package com.shiyi.handler;

import com.shiyi.service.IEmployeeService;
import com.shiyi.tx.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import sun.reflect.annotation.ExceptionProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: 十一
 * @Date: 2019-04-14 21:00
 * @Descrption
 **/
public class TransactionHandler implements InvocationHandler {

    private Object service;

    private TransactionManager tx;

    public void setService(Object service) {
        this.service = service;
    }

    public void setTx(TransactionManager tx) {
        this.tx = tx;
    }

    public Object getService() {
        return service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try{
            tx.begin();
            method.invoke(service,args);
            tx.commit();
        }catch (Exception e){
            tx.rollBack();
        }
        return null;
    }
}
