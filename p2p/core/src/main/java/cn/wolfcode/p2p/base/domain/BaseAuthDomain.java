package cn.wolfcode.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
/**
 * Created by wolfcode-lanxw
 */
@Setter@Getter
public class BaseAuthDomain extends BaseDomain {
    public static final int STATE_NORMAL = 0;//待审核
    public static final int STATE_PASS = 1;//审核通过
    public static final int STATE_REJECT = 2;//审核拒接
    protected LoginInfo applier;//申请人
    protected int state;//审核状态
    protected LoginInfo auditor;//审核人
    protected Date applyTime;//申请时间
    protected Date auditTime;//审核时间
    protected String remark;//审核备注
    public String getStateDisplay(){
        switch (this.state){
            case STATE_NORMAL:return "待审核";
            case STATE_PASS:return "审核通过";
            case STATE_REJECT:return "审核拒接";
            default:return "";
        }
    }
}
