package cn.wolfcode.crud.service;

import cn.wolfcode.crud.domain.Customertransfer;
import cn.wolfcode.crud.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface ICustomertransferService {

    void save(Customertransfer entity);

    PageInfo<Customertransfer> query(QueryObject qo);
}
