package cn.wolfcode.crm.domain;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

//domain的父类,抽取id属性
@Setter@Getter
public abstract class BaseDomain {
    protected Long id;

    public String getJson(){
        Class<? extends BaseDomain> clazz = this.getClass();
        Map<String,Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                if(!propertyDescriptor.getName().equals("json")){
                    map.put(propertyDescriptor.getName(),propertyDescriptor.getReadMethod().invoke(this,null));
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }
}
