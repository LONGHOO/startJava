package cn.wolfcode.p2p.base.util;

import cn.wolfcode.p2p.base.exception.DisplayException;

/**
 * @Author: 十一
 * @Date: 2019-06-12 19:34
 * @Descrption 断言工具类
 **/
public class AssertUtil {

    private AssertUtil(){};

    public static void hasLength(String str,String msg){
        if(null == str || str.trim().length() == 0 || str.equals("")){
            throw new DisplayException(msg);
        }
    }

    public static void isEquals(String str1,String str2,String msg){
        if(str1 == null && str2 == str1){
           return ;
        }
        if(!str1.equals(str2)){
            throw new DisplayException(msg);
        }
    }

    public static void isPhoneNumber(String number){
        String regex = "^1[3456789]\\d{9}";
        if(!number.matches(regex)){
            throw new DisplayException("不是正确的手机号码格式");
        }
    }
}
