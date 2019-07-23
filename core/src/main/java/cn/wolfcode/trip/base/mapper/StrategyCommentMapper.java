package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.query.StrategyQueryObject;

import java.util.List;

public interface StrategyCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StrategyComment record);

    StrategyComment selectByPrimaryKey(Long id);

    List<StrategyComment> selectAll();

    List<StrategyComment> queryList(StrategyQueryObject qo);

    int updateByPrimaryKey(StrategyComment record);
}