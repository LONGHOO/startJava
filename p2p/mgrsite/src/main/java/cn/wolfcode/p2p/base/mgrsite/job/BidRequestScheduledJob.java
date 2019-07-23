package cn.wolfcode.p2p.base.mgrsite.job;

import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.bussniss.domain.BidRequest;
import cn.wolfcode.p2p.bussniss.service.IBidRequestAuditHistoryService;
import cn.wolfcode.p2p.bussniss.service.IBidRequsetService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author: 十一
 * @Date: 2019-06-19 22:12
 * @Descrption
 **/
@Component
public class BidRequestScheduledJob implements ApplicationListener<ContextRefreshedEvent> {


    /**
     * 待处理标列表
     **/
    private ConcurrentLinkedQueue<BidRequest> queue = new ConcurrentLinkedQueue<>();

    @Autowired
    private IBidRequsetService bidRequsetService;

    @Autowired
    private IBidRequestAuditHistoryService historyService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        getListFromOneHourLater();
    }

    /**
     *功能描述 调度器一，一小时执行一次 查询未来一个小时将要发布的标
     * @author 十一
     * @return void
     * @date 2019-06-19 22:20
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void getListFromOneHourLater(){
        System.out.println("查询list-----------");
        List<BidRequest> list = bidRequsetService.getListFromOneHourLater(DateUtils.addHours(new Date(), 1));
        for (BidRequest bidRequest : list) {
            queue.add(bidRequest);
        }
    }

    /**
     *功能描述 发布调度任务，每分钟执行一次
     * @author 十一
     * @return
     * @date 2019-06-19 22:25
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void publishBidRequest(){
        System.out.println("queue"+queue);
        Date now = new Date();
        Iterator<BidRequest> itr = queue.iterator();
        if(itr.hasNext()){
            BidRequest bidRequest = itr.next();
            System.out.println(bidRequest.getPublishTime().toLocaleString());
            System.out.println(now.toLocaleString());
            if(bidRequest.getPublishTime().getTime()<=now.getTime()){
                //设置状态为招标中
                bidRequest.setBidRequestState(Constants.BIDREQUEST_STATE_BIDDING);
                bidRequsetService.saveOrUpdate(bidRequest);
                queue.remove(bidRequest);
            }
        }
    }

}
