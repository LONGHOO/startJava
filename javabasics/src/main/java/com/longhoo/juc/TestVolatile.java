package com.longhoo.juc;

/**
 * @Author: 十一
 * @Date: 2019-05-23 23:20
 * @Descrption
 **/
public class TestVolatile {

    public static void main(String[] args){
        ThreadDemo demo = new ThreadDemo();
        new Thread(demo).start();
        while(true){
            if(demo.isFlag()){
                System.out.println("the flag state is changed .....");
                break;
            }
        }
    }


}

class ThreadDemo implements Runnable{

    private volatile boolean flag = false;
    public boolean isFlag() {
        return flag;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(200);
            flag = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("flat is "+ isFlag());
    }
}