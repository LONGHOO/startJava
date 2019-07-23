package com.shiyi.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author: 十一
 * @Date: 2019-04-13 13:16
 * @Descrption
 **/
public class TeacherExistConditon implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        BeanDefinitionRegistry registry = conditionContext.getRegistry();
        System.out.println("_______________________________");

        System.out.println(registry.containsBeanDefinition("student"));
        return registry.containsBeanDefinition("student");
    }
}
