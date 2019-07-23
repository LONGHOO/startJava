package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.query.DictionaryItemQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDictionaryItemService {
    
    void saveOrUpdate(SystemDictionaryItem entity);
    void delete(Long id);

    List<SystemDictionaryItem> queryItemBySn(String sn);

    SystemDictionaryItem get(Long id);
    List<SystemDictionaryItem> list();

    PageInfo<SystemDictionaryItem> query(DictionaryItemQueryObject qo);
}
