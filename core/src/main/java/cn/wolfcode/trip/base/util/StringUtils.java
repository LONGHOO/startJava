package cn.wolfcode.trip.base.util;

/**
 * @Author: 十一
 * @Date: 2019-05-20 16:35
 * @Descrption 字符串工具类
 **/
public class StringUtils {

    private StringUtils(){}

    public static boolean hasLength(String str){
        return str != null && str.trim() != "" && str.trim().length() > 1;
    }
}
