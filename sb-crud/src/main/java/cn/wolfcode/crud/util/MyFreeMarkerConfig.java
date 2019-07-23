package cn.wolfcode.crud.util;

//import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

public class MyFreeMarkerConfig extends FreeMarkerConfigurer {
    /*@Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        //继承之前的属性配置，这步不能省
        super.afterPropertiesSet();
        Configuration cfg = this.getConfiguration();
        cfg.setSharedVariable("shiro", new ShiroTags());//shiro 标签
    }*/
}
