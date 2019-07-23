package cn.wolfcode.crm.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * @Author: 十一
 * @Date: 2019-05-09 16:50
 * @Descrption
 **/
public class MyFreeMarkerConfig extends FreeMarkerConfigurer {
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        Configuration cfg = getConfiguration();
        cfg.setSharedVariable("shiro",new ShiroTags());
    }
}
