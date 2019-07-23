package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

/**
 *功能描述：游记内容实体表
 * @date 2019-05-26 11:14
 * @author 十一
**/
@Getter
@Setter
public class TravelContent extends BaseDomain{

    /**
     * 文章内容
     */
    private String content;

}