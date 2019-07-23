package com.shiyi.service.impl;

import com.shiyi.service.IEmployeeService;
import com.shiyi.tx.TransactionManager;

/**
 * @Author: 十一
 * @Date: 2019-04-14 20:53
 * @Descrption
 **/
public class EmployeeServiceProxy implements IEmployeeService {

    private IEmployeeService service;

    private TransactionManager tx;

    public void setTx(TransactionManager tx) {
        this.tx = tx;
    }

    public void setService(IEmployeeService service) {
        this.service = service;
    }

    @Override
    public void save() {
        try{
            tx.begin();
            service.save();
            tx.commit();
        }catch(Exception e){
            tx.rollBack();
        }

    }
}
