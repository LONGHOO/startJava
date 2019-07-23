package com.shiyi.config;

import com.shiyi.aspect.LogAspects;
import com.shiyi.service.impl.UserServiceImpl01;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @Author: 十一
 * @Date: 2019-04-29 14:57
 * @Descrption
 **/
/**
 *
 * 结构：
 *  AnnotationAwareAspectJAutoProxyCreator
 *      ->AspectJAwareAdvisorAutoProxyCreator
 *          ->AbstractAdvisorAutoProxyCreator
 *              ->AbstractAutoProxyCreator
 *                  ->ProxyProcessorSupport
 *                  implemnets  SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware ;
 * 由于继承了BeanPostProcessor和BeanFactoryAware，所以我们从分类开始梳理跟BeanPostProcessor和BeanFactory有关的方法
 * ProxyProcessorSupport：postProcessAfterInitialization(),setBeanFactory()
 * AbstractAdvisorAutoProxyCreator:复写了setBeanFactory（）=>initBeanFactory()
 *
 * AnnotationAwareAspectJAutoProxyCreator->复写initBeanFactory（）
 *
 * 测试方法执行流程：
 *      1》传入配置类，创建IOC容器
 *      2》注册配置类，调用refresh()刷新容器
 *      3》registerBeanPostProcessors(beanFactory);注册bean的后置处理器，方便拦截Bean的创建
 */



@Configuration
@EnableAspectJAutoProxy
//@ComponentScan(value = "com.shiyi",
//        includeFilters ={@ComponentScan.Filter(type = FilterType.ANNOTATION,
//                value = {Service.class,Aspect.class})},useDefaultFilters = false)
public class AspectConfig {

    @Bean
    public LogAspects aspect(){
        return new LogAspects();
    }

    @Bean
    public UserServiceImpl01 service(){
        return new UserServiceImpl01();
    }
}
