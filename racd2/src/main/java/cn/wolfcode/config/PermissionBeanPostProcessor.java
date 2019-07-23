package cn.wolfcode.config;

import cn.wolfcode.crud.domain.Permission;
import cn.wolfcode.crud.mapper.PermissonMapper;
import cn.wolfcode.util.RequiredPermission;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * @Author: 十一
 * @Date: 2019-05-03 14:38
 * @Descrption
 **/
@Component
public class PermissionBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private PermissonMapper mapper;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initAllOfPermission();
    }

    public void initAllOfPermission(){
        mapper.deleteAll();
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(Controller.class);
        Collection<Object> controllers = map.values();
        Permission permission = new Permission();
        for (Object controller : controllers) {
            Method[] methods = controller.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if(method.isAnnotationPresent(RequiredPermission.class)){
                    RequiredPermission requiedPermission = method.getAnnotation(RequiredPermission.class);
                    String[] value = requiedPermission.value();
                    permission.setName(value[0]);
                    permission.setExpression(value[1]);
                    mapper.insert(permission);
                }
            }
        }
    }
}
