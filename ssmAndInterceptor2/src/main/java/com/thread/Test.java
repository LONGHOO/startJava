package com.thread;

/**
 * @Author: 十一
 * @Date: 2019-04-21 19:14
 * @Descrption
 **/
public class Test {
    public static void main(String[] args){
        PostMan a = new PostMan();
        Thread postMan1 = new Thread(a,"A");
//        postMan1.setName("postman1");
        Thread postMan2= new Thread(a,"B");
//        postMan2.setName("postman2");
        Thread postMan3 = new Thread(a,"C");
//        postMan3.setName("postman3");
        postMan1.start();
        postMan2.start();
        postMan3.start();
    }
}
