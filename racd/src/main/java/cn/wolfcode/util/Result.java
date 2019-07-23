package cn.wolfcode.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: 十一
 * @Date: 2019-05-03 19:08
 * @Descrption
 **/
@Getter
@Setter
public class Result {

    private String msg;
    private boolean success;
    private Object obj;

    public static Result getResult(String msg,Object obj){
        Result result = new Result();
        if(msg == null){
            result.success = true;
        }else{
            result.success = false;
        }
        result.msg = msg;
        result.obj = obj;
        return result;
    }
}
