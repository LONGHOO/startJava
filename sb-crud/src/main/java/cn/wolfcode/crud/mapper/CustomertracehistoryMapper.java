package cn.wolfcode.crud.mapper;

import cn.wolfcode.crud.domain.Customertracehistory;
import cn.wolfcode.crud.query.QueryObject;

import java.util.List;

public interface CustomertracehistoryMapper {

    int insert(Customertracehistory record);

    int updateByPrimaryKey(Customertracehistory record);

    List<Customertracehistory> selectForList(QueryObject qo);
}