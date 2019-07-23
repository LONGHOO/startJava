package cn.wolfcode.p2p.bussniss.mapper;

import cn.wolfcode.p2p.bussniss.domain.PaymentSchedule;
import java.util.List;

public interface PaymentScheduleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaymentSchedule record);

    PaymentSchedule selectByPrimaryKey(Long id);

    List<PaymentSchedule> selectAll();

    int updateByPrimaryKey(PaymentSchedule record);

    List<PaymentSchedule> selectByBorrowUserId(Long id);

    int countReturnFinishedByBorrowId(Long id);
}