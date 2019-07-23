package com.shiyi.component;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: 十一
 * @Date: 2019-04-26 21:19
 * @Descrption
 **/
public class MyFactoryBean implements FactoryBean<Color> {

    @Override
    public Color getObject() throws Exception {
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
