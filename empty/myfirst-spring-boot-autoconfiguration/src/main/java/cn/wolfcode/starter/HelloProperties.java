package cn.wolfcode.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: 十一
 * @Date: 2019-06-11 15:12
 * @Descrption
 **/
@ConfigurationProperties(prefix = "cn.wolfcode")
public class HelloProperties {

    private String prefix;

    private String subfix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSubfix() {
        return subfix;
    }

    public void setSubfix(String subfix) {
        this.subfix = subfix;
    }
}
