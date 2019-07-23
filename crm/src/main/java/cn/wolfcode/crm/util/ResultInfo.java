package cn.wolfcode.crm.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: 十一
 * @Date: 2019-05-06 20:27
 * @Descrption
 **/
@Getter
@Setter
public class ResultInfo {

    private ResultInfo(){

    }

    private boolean success = true;

    private String errorMsg ;

    public static ResultInfo success(){
        return new ResultInfo();
    }

    public static ResultInfo fail(String msg){
        ResultInfo result = new ResultInfo();
        result.setSuccess(false);
        result.setErrorMsg(msg);
        return result;
    }
}
