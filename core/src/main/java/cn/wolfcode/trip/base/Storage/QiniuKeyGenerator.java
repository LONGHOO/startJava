package cn.wolfcode.trip.base.Storage;

import java.text.MessageFormat;
import java.util.UUID;

/**
 * @Author: 十一
 * @Date: 2019-05-20 11:21
 * @Descrption 自定义七牛云上传图片生成的key
 **/
public class QiniuKeyGenerator {

    private static final String key = "{0}/{1}/{2}";

    /**
     *
     * 功能描述:
     * @params:  无
     * @return:  图片的key
     * @auther: 十一
     * @date:   2019-05-20 11:57
     */
    public static String generateKey(){
        return MessageFormat.format(key,"wolfcode","trip",getUUID());
    }
    
    /**
     *
     * 功能描述:
     * @params:
     * @return:  uuid
     * @auther: 十一
     * @date:   2019-05-20 11:48
     */
    public static String getUUID(){
        return  UUID.randomUUID().toString().replace("-", "hoo");
    }
}
