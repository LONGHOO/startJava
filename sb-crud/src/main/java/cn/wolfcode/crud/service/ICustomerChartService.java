package cn.wolfcode.crud.service;


import cn.wolfcode.crud.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface ICustomerChartService {

    List<Map<String,Object>> listCustomerCharts(QueryObject qo);
}
