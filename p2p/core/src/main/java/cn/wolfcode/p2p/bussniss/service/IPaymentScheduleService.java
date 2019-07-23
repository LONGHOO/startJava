package cn.wolfcode.p2p.bussniss.service;

import cn.wolfcode.p2p.bussniss.domain.PaymentSchedule;
import com.github.pagehelper.PageInfo;

/**
 * @Author: 十一
 * @Date: 2019-06-22 20:16
 * @Descrption
 **/
public interface IPaymentScheduleService {

    void saveOrUpdate(PaymentSchedule paymentSchedule);

    PageInfo<PaymentSchedule> selectByBorrowUserId(Long id);

    PaymentSchedule get(Long id);

    boolean isReturnMoneyFinished(PaymentSchedule id);
}
