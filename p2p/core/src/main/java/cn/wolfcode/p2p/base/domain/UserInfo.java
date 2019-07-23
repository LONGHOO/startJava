package cn.wolfcode.p2p.base.domain;

import cn.wolfcode.p2p.base.util.BitStateUtil;
import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.base.util.MaskUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class UserInfo extends BaseDomain{

    private int version;//乐观锁
    private Long bitState = 0L;//用户状态码
    private String realName;//真实姓名
    private String idNumber;//身份证号码
    private String phoneNumber;//手机号码
    private String email;//邮箱
    private SystemDictionaryItem incomeGrade;//收入情况
    private SystemDictionaryItem marriage;//婚姻情况
    private SystemDictionaryItem kidCount; //子女情况
    private SystemDictionaryItem educationBackground;//学历情况
    private SystemDictionaryItem houseCondition;//住房情况

    private Long realAuthId;//实名认证id
    private Long videoAuthId;

    /**
     *功能描述 添加状态
     * @author 十一
     * @return void
     * @date 2019-06-18 15:18
     */
    public void addState(Long state){
        this.bitState = BitStateUtil.addState(bitState,state);
    }

    /**
     *功能描述 移除状态
     * @author 十一
     * @return boolean
     * @date 2019-06-18 15:18
     */
    public void removeState(Long state){
        this.bitState = BitStateUtil.removeState(bitState,state);
    }

    public boolean isHasBindEmail(){
        if(!StringUtils.hasLength(this.email)){
            return true;
        }
        return false;
    }

    public boolean isHasBidRequest(){
        return BitStateUtil.hasState(this.bitState, BitStateUtil.HAS_BIDREQUEST_PROCESS);
    }

    /**
     *功能描述 是否填写基本资料
     * @author 十一
     * @param []
     * @return boolean
     * @date 2019-06-15 22:00
     */
    public boolean isHasBasicInfo(){
        return BitStateUtil.hasState(this.bitState, BitStateUtil.HAS_BASICINFO);
    }

    /**
     *功能描述 是否完成身份认证
     * @author 十一
     * @param []
     * @return boolean
     * @date 2019-06-15 22:00
     */
    public boolean isHasRealAuth(){
        return  BitStateUtil.hasState(this.bitState, BitStateUtil.HAS_REALAUTH);
    }

    /**
     *功能描述 是否完成视频认证
     * @author 十一
     * @param []
     * @return boolean
     * @date 2019-06-15 22:00
     */
    public boolean isHasVideoAuth(){
        return BitStateUtil.hasState(this.bitState, BitStateUtil.HAS_VIDEOAUTH);
    }

    public boolean isCanBorrow(){
        return this.isHasBasicInfo() && this.isHasRealAuth()&& this.isHasVideoAuth();
    }

    public String getAnonymousRealName(){
        return MaskUtil.getAnonymousRealName(realName);
    }

    public String getIdNumber(){
        return MaskUtil.getAnonymousIdNumber(idNumber);
    }
}