package com.longhoo.nio;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * @Author: 十一
 * @Date: 2019-03-15 08:52
 * @Descrption
 **/
public class NIODemo {

    @Test
    public void testNIO(){
        ByteBuffer buff = ByteBuffer.allocateDirect(1024);
        String  str = "hello world";
        buff.put(str.getBytes());
        System.out.println(buff.capacity());
        System.out.println(buff.limit());
        System.out.println(buff.position());
        buff.put("abc".getBytes());
        System.out.println(buff.capacity());
        System.out.println(buff.position());
        System.out.println(buff.limit());
        buff.rewind();
        System.out.println("=================");
        System.out.println(buff.capacity());
        System.out.println(buff.position());
        System.out.println(buff.limit());
    }
}
