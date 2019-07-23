package com.shiyi.config;

import com.shiyi.component.Dog;
import com.shiyi.component.MyImportBeanDefinitionRegister;
import com.shiyi.conditional.WindowsConditional;
import com.shiyi.selector.MySelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 *@ComponetSan指定要扫描的包
 * excludeFilters：表示要排除的过滤条件
 * type：按照指定的规则进行排除，FilterType.ANNOTATION:表示根据注解信息进行排除，
 * FilterType。ASSIGNABLE_TYPE根据类型进行排除
 *
 * 功能描述:
 * @param:
 * @return:
 * @auther: 十一
 * @date:
 */
@Configuration
@Conditional({WindowsConditional.class})
@Import({MySelector.class, MyImportBeanDefinitionRegister.class})
public class BeanConfig {

//    @Bean
//    public Person person(){
//        System.out.println("Person bean be created");
//        Person person = new Person();
//        person.setAge(12);
//        person.setName("zhangsna");
//        return person;
//
//    }
//
//    @Conditional({WindowsConditional.class})
//    @Bean("bill")
//    public Person person01(){
//        return new Person("Bill Gates",58);
//    }
//
//
//    @Conditional({LinuxConiditional.class})
//    @Bean("linux")
//    public Person perosn04(){
//        return new Person("linus",46);
//    }
//
//    @Bean
//    public MyFactoryBean factoryBean(){
//        return new MyFactoryBean();
//    }
//
//
//    @Bean(initMethod = "init",destroyMethod = "destory")
//    public Car car(){
//        return new Car();
//
//    }
//
//
//    @Bean
//    public Cat cat(){
//        return new Cat();
//    }


    @Bean
    public Dog dog(){
        return new Dog();
    }
}

