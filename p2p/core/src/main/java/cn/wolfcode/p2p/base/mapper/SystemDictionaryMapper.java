package cn.wolfcode.p2p.base.mapper;

import cn.wolfcode.p2p.base.domain.SystemDictionary;
import cn.wolfcode.p2p.base.query.QueryObject;

import java.util.List;

public interface SystemDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Long id);

    List<SystemDictionary> selectAll();

    int updateByPrimaryKey(SystemDictionary record);

    List<SystemDictionary> query(QueryObject qo);
}