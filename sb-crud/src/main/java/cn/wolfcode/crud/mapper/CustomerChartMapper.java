package cn.wolfcode.crud.mapper;

import cn.wolfcode.crud.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface CustomerChartMapper {

    //潜在客户报表
    List<Map<String ,Object>> selectCustomerChart(QueryObject qo);
}
