package cn.wolfcode.p2p.bussniss.service.impl;

import cn.wolfcode.p2p.bussniss.domain.PaymentScheduleDetail;
import cn.wolfcode.p2p.bussniss.mapper.PaymentScheduleDetailMapper;
import cn.wolfcode.p2p.bussniss.service.IPaymentScheduleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 十一
 * @Date: 2019-06-22 20:44
 * @Descrption
 **/
@Service
@Transactional
public class PaymentScheduleDetailServiceImpl implements IPaymentScheduleDetailService {

    @Autowired
    private PaymentScheduleDetailMapper detailMapper;

    @Override
    public void saveOrUpdate(PaymentScheduleDetail paymentScheduleDetail) {
        if(paymentScheduleDetail.getId() != null){
            detailMapper.updateByPrimaryKey(paymentScheduleDetail);
        }else{
            detailMapper.insert(paymentScheduleDetail);
        }
    }
}
