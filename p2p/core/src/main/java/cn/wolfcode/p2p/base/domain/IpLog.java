package cn.wolfcode.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IpLog extends BaseDomain{

    public static final int STATE_FAILED = 0;
    public static final int STATE_SUCCESS = 1;

    private String username;

    private Date logintime;

    private String ip;

    private int state = -1;

    private int usertype = -1;

    public String getStateDisplay(){
        if(this.state == STATE_SUCCESS) {
            return "登陆成功";
        }
        return "登陆失败";
    }
    public String getUserTypeDisplay(){
        if(this.usertype == LoginInfo.USERTYPE_USER) {
            return "普通用户";
        }
        return "管理员";
    }
}