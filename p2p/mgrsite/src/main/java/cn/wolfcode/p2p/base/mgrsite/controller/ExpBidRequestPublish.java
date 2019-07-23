package cn.wolfcode.p2p.base.mgrsite.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.bussniss.query.BidRequestQueryObject;
import cn.wolfcode.p2p.base.util.AjaxResult;
import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.bussniss.domain.BidRequest;
import cn.wolfcode.p2p.bussniss.service.IBidRequestAuditHistoryService;
import cn.wolfcode.p2p.bussniss.service.IBidRequsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-06-25 17:13
 * @Descrption 体验标处理类
 **/
@Controller
public class ExpBidRequestPublish {


    @Autowired
    private IBidRequsetService bidRequsetService;

    @Autowired
    private IBidRequestAuditHistoryService historyService;

    /**
     *功能描述 跳转到体验标发布界面
     * @author 十一
     * @return
     * @date 2019-06-25 17:14
     */
    @RequestMapping("expBidRequestPage")
    public String expBidRequestPublish(Model model){
        model.addAttribute("minBidRequestAmount", Constants.BORROW_AMOUNT_MIN);
        model.addAttribute("minBidAmount", Constants.BID_AMOUNT_MIN);
        return  "expbidrequest/expbidrequestpublish";
    }

    /** 发布体验标
     *功能描述
     * @author 十一
     * @return
     * @date 2019-06-25 17:21
     */
    @LoginRequird
    @RequestMapping("expBidRequestPublish")
    @ResponseBody
    public AjaxResult expBidRequestPublish(BidRequest bidRequest){
        AjaxResult result = null;
        try{
            bidRequsetService.borrowApply(bidRequest, Constants.BIDREQUEST_TYPE_PUBLISH);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

    @LoginRequird
    @RequestMapping("expBidRequest_list")
    public String expBidRequestList(@ModelAttribute("qo") BidRequestQueryObject qo, Model model){
        qo.setType(Constants.BIDREQUEST_TYPE_PUBLISH);
        model.addAttribute("pageInfo",bidRequsetService.query(qo));
        return "expbidrequest/expbidrequestlist";
    }
}
