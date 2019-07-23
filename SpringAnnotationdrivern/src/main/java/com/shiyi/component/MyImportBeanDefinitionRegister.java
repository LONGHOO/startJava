package com.shiyi.component;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: 十一
 * @Date: 2019-04-25 22:25
 * @Descrption
 **/
public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        boolean condition = registry.containsBeanDefinition("person");
        if(condition){
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
            rootBeanDefinition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
            registry.registerBeanDefinition("rainBow",rootBeanDefinition);
        }
    }
}
