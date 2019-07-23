package com.shiyi.config;

import com.shiyi.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Author: 十一
 * @Date: 2019-04-27 17:08
 * @Descrption
 **/
@Configuration
@EnableWebMvc
public class SpringEnvConfig  extends WebMvcConfigurerAdapter  {

    /**
     *
     * 功能描述:配置静态资源
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     *
     * 功能描述:视图解析器
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor());
    }
}
