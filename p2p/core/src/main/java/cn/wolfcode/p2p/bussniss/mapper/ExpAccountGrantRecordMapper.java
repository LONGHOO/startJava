package cn.wolfcode.p2p.bussniss.mapper;

import cn.wolfcode.p2p.bussniss.domain.ExpAccountGrantRecord;
import java.util.List;

public interface ExpAccountGrantRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExpAccountGrantRecord record);

    ExpAccountGrantRecord selectByPrimaryKey(Long id);

    List<ExpAccountGrantRecord> selectAll();

    int updateByPrimaryKey(ExpAccountGrantRecord record);
}