package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyService {
    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);

    /**
     * 新增和编辑
     * @param strategy
     */
    void saveOrUpdate(Strategy strategy);


    List<Strategy> listAll();


    List<Strategy> getListByState(Integer state);

    List<Strategy> getHot(StrategyQueryObject qo);

    Strategy getById(Long id);
}
