package cn.wolfcode.crud.mapper;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.domain.Systemdictionary;
import cn.wolfcode.crud.query.QueryObject;

import java.util.List;

public interface SystemdictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Systemdictionary record);

    Systemdictionary selectByPrimaryKey(Long id);

    List<Systemdictionary> selectAll();

    int updateByPrimaryKey(Systemdictionary record);

    List<Systemdictionary> selectForList(QueryObject qo);
}