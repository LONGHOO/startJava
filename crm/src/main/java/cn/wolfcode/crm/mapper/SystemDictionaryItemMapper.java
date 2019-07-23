package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.query.DictionaryItemQueryObject;

import java.util.List;

public interface SystemDictionaryItemMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    List<SystemDictionaryItem> selectAll();

    List<SystemDictionaryItem> selectByParentId(DictionaryItemQueryObject queryObject);

    int updateByPrimaryKey(SystemDictionaryItem record);

    List<SystemDictionaryItem> queryItemBySn(String sn);
}