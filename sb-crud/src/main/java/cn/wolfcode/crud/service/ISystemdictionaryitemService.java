package cn.wolfcode.crud.service;

import cn.wolfcode.crud.domain.Systemdictionaryitem;
import cn.wolfcode.crud.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISystemdictionaryitemService {

    void saveOrUpdate(Systemdictionaryitem entity);

    void delete(Long id);

    Systemdictionaryitem get(Long id);

    List<Systemdictionaryitem> list();

    PageInfo<Systemdictionaryitem> query(QueryObject qo);

    List<Systemdictionaryitem> listItemByDicSn(String dicSn);
}
