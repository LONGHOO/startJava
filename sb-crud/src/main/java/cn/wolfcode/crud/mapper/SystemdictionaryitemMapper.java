package cn.wolfcode.crud.mapper;

import cn.wolfcode.crud.domain.Systemdictionary;
import cn.wolfcode.crud.domain.Systemdictionaryitem;
import cn.wolfcode.crud.query.QueryObject;

import java.util.List;

public interface SystemdictionaryitemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Systemdictionaryitem record);

    Systemdictionaryitem selectByPrimaryKey(Long id);

    List<Systemdictionaryitem> selectAll();

    int updateByPrimaryKey(Systemdictionaryitem record);

    List<Systemdictionaryitem> selectForList(QueryObject qo);

    List<Systemdictionaryitem> selectItemByDicSn(String dicSn);
}