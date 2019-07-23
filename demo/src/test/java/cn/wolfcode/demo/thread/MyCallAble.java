package cn.wolfcode.demo.thread;

import java.util.concurrent.Callable;

/**
 * @Author: 十一
 * @Date: 2019-06-30 22:12
 * @Descrption
 **/
public class MyCallAble implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println("this is run faucntion");
        Thread.sleep(2000);
        return "hello world";
    }

}
