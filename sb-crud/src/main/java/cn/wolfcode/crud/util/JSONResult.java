package cn.wolfcode.crud.util;

import lombok.Getter;

@Getter
public class JSONResult {
    //默认操作成功
    private boolean success = true;
    //错误信息
    private String msg;
    public void mark(String msg){
        this.success = false;
        this.msg = msg;
    }

}
