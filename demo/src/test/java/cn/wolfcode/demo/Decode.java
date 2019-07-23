package cn.wolfcode.demo;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * @Author: 十一
 * @Date: 2019-07-07 21:34
 * @Descrption
 **/
public class Decode {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String code = "e4bda0e698afe68891e79a84e88396e58fafe788b1";
        String tcode = "e4 bd a0 e6 98 af e6 88 91 e7 9a 84 e8 83 96 e5 8f af e7 88 b1";
        String[] strs = tcode.split(" ");
        byte[] bytes = new byte[strs.length];
        for (int i = 0; i < strs.length; i++) {
            bytes[i] = (Integer.valueOf(strs[i],16).byteValue());
        }
        System.out.println(new String(bytes,"utf8"));
    }
}
