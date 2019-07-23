package com.shiyi.tx;

/**
 * @Author: 十一
 * @Date: 2019-04-14 19:40
 * @Descrption
 **/
public class TransactionManager {

    public void begin(){
        System.out.println("begin");
    }

    public void commit(){
        System.out.println("commit");
    }

    public void rollBack(){
        System.out.println("rollback");
    }
}
