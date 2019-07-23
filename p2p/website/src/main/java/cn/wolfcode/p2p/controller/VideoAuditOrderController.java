package cn.wolfcode.p2p.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.domain.UserInfo;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.service.ILoginInfoService;
import cn.wolfcode.p2p.base.service.IOrderTimeService;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.service.IVideoAuthService;
import cn.wolfcode.p2p.base.util.AjaxResult;
import cn.wolfcode.p2p.base.util.BitStateUtil;
import java.util.List;

import cn.wolfcode.p2p.base.util.DateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-06-18 19:02
 * @Descrption
 **/
@Controller
public class VideoAuditOrderController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IVideoAuthService videoAuthService;

    @Autowired
    private ILoginInfoService loginInfoService;

    @Autowired
    private IOrderTimeService orderTimeService;

    @RequestMapping("videoAuditOrder")
    @LoginRequird
    public String videoAuditOrder(Model model){
        //如果审核成功，跳转到vodeoOrder——result界面
        UserInfo info = userInfoService.getCurrent();
        if(info.isHasVideoAuth()){
            model.addAttribute("auditing",false);
            return "videoOrder_result";
        }else{
            //如果正在审核，
            if(info.getVideoAuthId() != null){
                //videoAuth
                model.addAttribute("auditing",true);
                model.addAttribute("videoAuth",videoAuthService.get(info.getVideoAuthId()));
                return "videoOrder_result";
            }else{
                //auditors,查询所有的客服
                model.addAttribute("auditors",loginInfoService.selectByAuditor());
                //orderDates，预约的日期
                List<Date> list = new ArrayList<>();
                Date now = new Date();
                list.add(now);
                list.add(DateUtils.addDays(now,1));
                list.add(DateUtils.addDays(now,2));
                list.add(DateUtils.addDays(now,3));
                list.add(DateUtils.addDays(now,4));
                list.add(DateUtils.addDays(now,5));
                list.add(DateUtils.addDays(now,6));
                model.addAttribute("orderDates", list);
                //orderTimes，预约的时间
                model.addAttribute("orderTimes", orderTimeService.selectAll());
                return "videoOrder";
            }
        }
    }

    @LoginRequird
    @RequestMapping("videoAuth_apply")
    @ResponseBody
    public AjaxResult videoAuth_apply(Long timeid,String orderDate,Long auditorId){
        AjaxResult result = null;
        try{
            videoAuthService.videoAuthApply(timeid, orderDate, auditorId);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
