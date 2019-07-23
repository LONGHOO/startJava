package com.shiyi.conditional;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author: 十一
 * @Date: 2019-04-25 20:52
 * @Descrption
 **/
public class LinuxConiditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        System.out.println(environment);
        String osName = environment.getProperty("os.name");
        if(osName.contains("linux")){
            return true;
        }
        return false;

    }
}
