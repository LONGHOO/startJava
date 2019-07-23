package cn.wolfcode.p2p.base.service;

import cn.wolfcode.p2p.base.domain.SystemDictionary;
import cn.wolfcode.p2p.base.domain.SystemDictionaryItem;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.query.SystemDictionaryQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-06-12 13:43
 * @Descrption
 **/
public interface ISystemDictionaryItemService {

    void saveOrUpdate(SystemDictionaryItem dictionary);

    List<SystemDictionaryItem> selectAll();

    PageInfo<SystemDictionaryItem> query(SystemDictionaryQueryObject qo);

    void deleteById(Long id);

    void deleteByParentId(Long parentId);

    List<SystemDictionaryItem> getBySn(String educationBackground);

}
