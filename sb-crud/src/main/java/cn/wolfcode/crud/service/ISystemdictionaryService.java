package cn.wolfcode.crud.service;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.domain.Systemdictionary;
import cn.wolfcode.crud.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISystemdictionaryService {

    void saveOrUpdate(Systemdictionary entity);

    void delete(Long id);

    Systemdictionary get(Long id);

    List<Systemdictionary> list();

    PageInfo<Systemdictionary> query(QueryObject qo);

}
