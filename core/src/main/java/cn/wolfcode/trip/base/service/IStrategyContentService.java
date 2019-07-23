package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyContentService {
    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);

    /**
     * 新增和编辑
     * @param
     */
    void saveOrUpdate(StrategyContent content);


    List<StrategyContent> listAll();

    StrategyContent getById(Long id);

}
