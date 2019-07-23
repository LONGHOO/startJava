package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息实体类
 * @author 十一
 */
@Setter
@Getter
public class User extends BaseDomain{

    public static final Integer GENDER_MALE = 1;
    public static final Integer GENDER_FEMALE = 0;
    public static final Integer GENDER_SECURITY = -1;

    private String email;

    private String nickName;

    private String password;

    private String place;

    private String headImgUrl;

    private Integer gender;

    private String coverImgUrl;

    private String sign;

    public String getGenderName(){
        String name = "保密";
        if(GENDER_MALE.equals(gender)){
            name = "男";
        }
        if(GENDER_FEMALE.equals(gender)){
            name = "女";
        }
        return name;
    }

}