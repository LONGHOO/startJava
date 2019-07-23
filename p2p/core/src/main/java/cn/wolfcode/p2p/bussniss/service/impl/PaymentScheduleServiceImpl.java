package cn.wolfcode.p2p.bussniss.service.impl;

import cn.wolfcode.p2p.bussniss.domain.BidRequest;
import cn.wolfcode.p2p.bussniss.domain.PaymentSchedule;
import cn.wolfcode.p2p.bussniss.mapper.PaymentScheduleMapper;
import cn.wolfcode.p2p.bussniss.service.IBidRequsetService;
import cn.wolfcode.p2p.bussniss.service.IPaymentScheduleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 十一
 * @Date: 2019-06-22 20:17
 * @Descrption
 **/
@Service
@Transactional
public class PaymentScheduleServiceImpl implements IPaymentScheduleService {

    @Autowired
    private PaymentScheduleMapper paymentScheduleMapper;

    @Autowired
    private IBidRequsetService bidRequsetService;

    @Override
    public void saveOrUpdate(PaymentSchedule paymentSchedule) {
        if(paymentSchedule.getId() == null){
            paymentScheduleMapper.insert(paymentSchedule);
        }else{
            paymentScheduleMapper.updateByPrimaryKey(paymentSchedule);
        }
    }

    @Override
    public PageInfo<PaymentSchedule> selectByBorrowUserId(Long id) {
        return new PageInfo<>(paymentScheduleMapper.selectByBorrowUserId(id));
    }

    @Override
    public PaymentSchedule get(Long id) {
        return paymentScheduleMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean isReturnMoneyFinished(PaymentSchedule schedule) {
        BidRequest bidRequest = bidRequsetService.getByPrimaryKey(schedule.getBidRequestId());
        int finishCount = paymentScheduleMapper.countReturnFinishedByBorrowId(schedule.getBorrowUser().getId());
        if(bidRequest.getMonthes2Return()==finishCount){
            return true;
        }
        return false;
    }
}
