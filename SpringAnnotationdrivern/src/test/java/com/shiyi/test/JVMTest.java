package com.shiyi.test;

import  java.util.*;

import java.util.ArrayList;

/**
 * @Author: 十一
 * @Date: 2019-05-05 13:28
 * @Descrption
 **/
public class JVMTest {

    private byte[] byteArrays = new byte[1024 * 1024];

    public static void main(String[] args){
//        long maxMemory = Runtime.getRuntime().maxMemory();
//        long totalMemory = Runtime.getRuntime().totalMemory();
//        System.out.println("maxMemory+.."+maxMemory+" (字节） "+maxMemory / 1024/1024+"MB");
//        System.out.println("totalMemory+.."+totalMemory+" (字节） "+totalMemory /1024/1024+"MB");
        List<JVMTest> list = new ArrayList<JVMTest>();
        try{
            for(int i = 0 ;i< 100;i++){
                list.add(new JVMTest());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
