package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.query.StrategyQueryObject;

import java.util.List;

public interface IStrategyCommentService {

    void saveOrUpdate(StrategyComment strategyComment,String[] tags);

    List<StrategyContent> listAll();

    StrategyComment getById(Long id);

    List<StrategyComment> queryList(StrategyQueryObject qo);
}
