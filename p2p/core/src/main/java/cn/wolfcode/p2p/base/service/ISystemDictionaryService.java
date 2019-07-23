package cn.wolfcode.p2p.base.service;

import cn.wolfcode.p2p.base.domain.SystemDictionary;
import cn.wolfcode.p2p.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-06-12 13:43
 * @Descrption
 **/
public interface ISystemDictionaryService {

    void saveOrUpdate(SystemDictionary dictionary);

    List<SystemDictionary> selectAll();

    PageInfo<SystemDictionary> query(QueryObject qo);

    void deleteById(Long id);
}
