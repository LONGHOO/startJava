package cn.wolfcode.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class LoginInfo extends BaseDomain{

    public static final int STATE_NORMAL = 0;
    public static final int STATE_lOCK = 1;
    public static final int USERTYPE_USER = 0;//普通用户
    public static final int USERTYPE_MANAGER = 1;//管理员
    //用户名
    private String username;
    //密码
    private String password;
    //用户类型
    private int userType;
    //是否客服
    private int isAuditor;
    //用户的状态
    private int state = STATE_NORMAL;

}