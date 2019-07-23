package cn.wolfcode.javaconfig;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: 十一
 * @Date: 2019-06-09 19:03
 * @Descrption
 **/
@Getter
@Setter
@ToString
public class Apple {

    private String name;

    private void init(){
        System.out.println("this is init method");
    }

    private void destoryMethod(){
        System.out.println("this is destory method");
    }
}
