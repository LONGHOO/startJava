package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: 十一
 * @Date: 2019-05-10 19:55
 * @Descrption
 **/
@Setter
@Getter
public class DictionaryItemQueryObject extends QueryObject {

    private Long parentId;

    private String title;
}
