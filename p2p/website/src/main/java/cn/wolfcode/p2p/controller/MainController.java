package cn.wolfcode.p2p.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.bussniss.query.BidRequestQueryObject;
import cn.wolfcode.p2p.bussniss.service.IBidRequsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 十一
 * @Date: 2019-06-19 21:21
 * @Descrption
 **/
@Controller
public class MainController {

    @Autowired
    private IBidRequsetService bidRequsetService;

    @LoginRequird
    @RequestMapping("main")
    public String mainPage(Model model, BidRequestQueryObject qo){
        //待发布的标
        qo.setState(Constants.BIDREQUEST_STATE_PUBLISH_PENDING);
        model.addAttribute("publishPendngBidRequests",bidRequsetService.query(qo).getList());
        //bidRequests
        qo.setState(null);
        //1.投标中； 2，还款中；3,已完成
        qo.setStates(new int[]{
                Constants.BIDREQUEST_STATE_BIDDING,
                Constants.BIDREQUEST_STATE_PAYING_BACK,
                Constants.BIDREQUEST_STATE_COMPLETE_PAY_BACK});
        model.addAttribute("bidRequests",bidRequsetService.query(qo).getList());
        qo.setStates(null);
        qo.setType(Constants.BIDREQUEST_TYPE_PUBLISH);
        qo.setBidRequestState(Constants.BIDREQUEST_STATE_BIDDING);
        model.addAttribute("expBidRequests",bidRequsetService.query(qo).getList());
        return "main";
    }
}
