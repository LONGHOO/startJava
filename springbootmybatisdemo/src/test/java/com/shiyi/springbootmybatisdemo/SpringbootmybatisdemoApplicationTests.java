package com.shiyi.springbootmybatisdemo;

import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class SpringbootmybatisdemoApplicationTests {

    @Test
    public void contextLoads() throws  Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql:///javaweb";
        String userName = "root";
        String passWorld = "longhoo638411";
        Connection conn = DriverManager.getConnection(url, userName, passWorld);
        PreparedStatement pre = conn.prepareStatement("select * from product");
        System.out.println(pre);
        ResultSet result = pre.executeQuery();
        while(result.next()){
            System.out.println(result.getObject(1)+""+result.getObject(2)
                    + ""+result.getObject(3)
                    +""+result.getObject(4));
        }
        conn.close();
    }

}
