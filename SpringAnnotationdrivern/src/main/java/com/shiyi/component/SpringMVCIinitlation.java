package com.shiyi.component;

import com.shiyi.config.SpringConfig;
import com.shiyi.config.SpringMVCCofnig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Author: 十一
 * @Date: 2019-04-27 16:04
 * @Descrption
 **/
public class SpringMVCIinitlation extends AbstractAnnotationConfigDispatcherServletInitializer {

    //配置Spring的配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    //配置SpringMVC的配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMVCCofnig.class};
    }

    //SpringMVC的映射路径
    //使用"/"会拦截除了.jsp的请求会交给DefaultServlet去处理，其他都会被SpringMVC所拦截包括静态资源*.js,*.html
    //使用"/*"会拦截所有的请求，包括*.jsp
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
