package cn.wolfcode.p2p.bussniss.domain;

import cn.wolfcode.p2p.base.domain.BaseAuthDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * 审核记录
 */
@Setter@Getter
public class BidRequestAuditHistory extends BaseAuthDomain {
    //发标审核
    public static final int TYPE_PUBLISH = 0;
    //满标一审
    public static final int TYPE_AUDIT1 = 1;
    //满标二审
    public static final int TYPE_AUDIT2 = 2;
    //借款id
    private Long bidRequestId;
    //审核类型
    private int auditType;
    public String getAuditTypeDisplay(){
        switch (this.auditType){
            case TYPE_PUBLISH:return "发标前审核";
            case TYPE_AUDIT1:return "满标一审";
            case TYPE_AUDIT2:return "满标二审";
            default:return "";
        }
    }
}
