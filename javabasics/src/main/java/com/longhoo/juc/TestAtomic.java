package com.longhoo.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 十一
 * @Date: 2019-05-24 00:56
 * @Descrption
 **/
public class TestAtomic {

    public static void main(String[] args){
        Atomic atomic = new Atomic();
        for (int i = 0; i < 10; i++) {
            new Thread(atomic).start();
        }
    }


}
class Atomic implements Runnable{

    private AtomicInteger value = new AtomicInteger();

    private int add(){
        return value.getAndIncrement();
    }
    @Override
    public void run() {
        try{
            Thread.sleep(200);
            System.out.println(add());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}