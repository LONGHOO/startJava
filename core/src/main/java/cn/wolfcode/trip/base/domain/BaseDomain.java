package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * domain的父类,抽取id属性
 * @author 十一
 */
@Setter@Getter
public abstract class BaseDomain {
    protected Long id;
}
