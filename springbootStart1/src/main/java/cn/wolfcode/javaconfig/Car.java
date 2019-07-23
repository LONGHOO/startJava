package cn.wolfcode.javaconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

/**
 * @Author: 十一
 * @Date: 2019-04-26 21:34
 * @Descrption
 **/
@Getter
@Setter
@ToString
public class Car {

    private ApplicationContext applicationContext;

    @Value("${car.brand}")
    private String brand;
    @Value("SUV☸️")
    private String type;
    @Value("1999000")
    private String salePrice;

    public Car(){
        System.out.println("car constructor....");
    }

    public void init(){
        System.out.println("init method be called....");
    }

    public void destory(){
        System.out.println("destory method be called....");
    }

}
