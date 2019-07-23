package cn.wolfcode.p2p.bussniss.service;

import cn.wolfcode.p2p.bussniss.domain.PaymentScheduleDetail;

/**
 * @Author: 十一
 * @Date: 2019-06-22 20:43
 * @Descrption
 **/
public interface IPaymentScheduleDetailService {

    void saveOrUpdate(PaymentScheduleDetail paymentScheduleDetail);
}
