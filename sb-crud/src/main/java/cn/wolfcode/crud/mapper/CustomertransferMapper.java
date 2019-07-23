package cn.wolfcode.crud.mapper;

import cn.wolfcode.crud.domain.Customertransfer;
import cn.wolfcode.crud.query.QueryObject;

import java.util.List;

public interface CustomertransferMapper {

    int insert(Customertransfer record);

    List<Customertransfer> selectForList(QueryObject qo);






    /*int deleteByPrimaryKey(Long id);
    Customertransfer selectByPrimaryKey(Long id);
    List<Customertransfer> selectAll();
    int updateByPrimaryKey(Customertransfer record);*/
}