package cn.wolfcode.p2p.bussniss.mapper;

import cn.wolfcode.p2p.bussniss.domain.SystemAccountFlow;
import java.util.List;

public interface SystemAccountFlowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemAccountFlow record);

    SystemAccountFlow selectByPrimaryKey(Long id);

    List<SystemAccountFlow> selectAll();

    int updateByPrimaryKey(SystemAccountFlow record);
}