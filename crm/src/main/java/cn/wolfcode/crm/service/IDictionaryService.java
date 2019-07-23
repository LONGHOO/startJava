package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SystemDictionary;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDictionaryService {

    void saveOrUpdate(SystemDictionary entity);

    void delete(Long id);

    SystemDictionary get(Long id);

    List<SystemDictionary> list();

    PageInfo<SystemDictionary> query(QueryObject qo);


}
