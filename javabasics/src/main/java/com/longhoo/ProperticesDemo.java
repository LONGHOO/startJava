package com.longhoo;


import java.io.IOException;
import java.util.Properties;

/**
 *
 * 功能描述:
 * @param:  
 * @return: 
 * @auther: 十一
 * @date:    
 */
public class ProperticesDemo{

    private String name;

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("test.properties"));
            System.out.println(new String(properties.getProperty("name").getBytes("ISO-8859-1"),
                    "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        new Object();
        }
    }


}