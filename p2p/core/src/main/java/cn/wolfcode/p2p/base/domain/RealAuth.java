package cn.wolfcode.p2p.base.domain;

import cn.wolfcode.p2p.base.util.MaskUtil;
import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class RealAuth extends BaseDomain{

    public static final int SEX_MALE = 0;//性别男
    public static final int SEX_FEMALE = 1;//性别女
    public static final int STATE_NORMAL = 0;//待审核
    public static final int STATE_PASS = 1;//审核通过
    public static final int STATE_REJECT = 2;//审核拒接
    private String realName;//真实姓名
    private int sex;//性别
    private String idNumber;//身份证号码
    private String bornDate;//出生年月
    private String address;//身份证地址
    private String image1;//身份证正面
    private String image2;//身份证反面

    private LoginInfo applier;//申请人
    private int state;//审核状态
    private LoginInfo auditor;//审核人
    private Date applyTime;//申请时间
    private Date auditTime;//审核时间
    private String remark;//审核备注

    public String getSexDisplay(){
        if(sex == SEX_FEMALE){
            return "女";
        }
        return "男";
    }

    /**
     *功能描述 审核状态回显
     * @author 十一
     * @return java.lang.String
     * @date 2019-06-17 22:02
     */
    public String getStateDisplay(){
        String stateResult  = "审核拒绝";
        if(state==STATE_NORMAL){
            stateResult = "待审核";
        }else if(state ==STATE_PASS){
            stateResult = "审核通过";
        }
        return stateResult;
    }

    public String getJsonString(){
        Map<Object, Object> map = new HashMap<>(16);
        map.put("id", id);
        map.put("realName", realName);
        map.put("username", applier.getUsername());
        map.put("sex", getSexDisplay());
        map.put("idNumber", idNumber);
        map.put("bornDate", bornDate);
        map.put("address", address);
        map.put("image1", image1);
        map.put("image2", image2);
        map.put("state", getStateDisplay());
        map.put("applyTime", applyTime);
        String result = JSONUtils.toJSONString(map);
        return result;
    }

    public String getAnonymousRealName(){
        return MaskUtil.getAnonymousRealName(realName);
    }

    public String getAnonymousIdNumber(){
        return MaskUtil.getAnonymousIdNumber(idNumber);
    }

    public String getAnonymousAddress(){
        return MaskUtil.getAnonymousRealName(address);
    }
}