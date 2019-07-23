package com.shiyi;

import com.shiyi.handler.TransactionHandler;
import com.shiyi.service.impl.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Proxy;


/**
 * @Author: 十一
 * @Date: 2019-04-13 20:31
 * @Descrption
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DynamicByCglib {

    @Autowired
    private TransactionHandler tx;


    @Test
    public void testInsert() throws Exception {
        Enhancer eh = new Enhancer();
        eh.setSuperclass(tx.getService().getClass());
        eh.setCallback(tx);
        EmployeeServiceImpl emp = (EmployeeServiceImpl) eh.create();
        emp.save();


    }

    @Test
    public  void testThread(){
        Runnable r1 = ()->{
            System.out.println("hello");
        };
        new Thread(r1).start();
        new Thread(()->{
            System.out.println("helo");
        }).start();
    }



}
