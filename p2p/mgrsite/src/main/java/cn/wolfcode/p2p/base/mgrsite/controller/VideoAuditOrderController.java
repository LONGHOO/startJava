package cn.wolfcode.p2p.base.mgrsite.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.domain.UserInfo;
import cn.wolfcode.p2p.base.domain.VideoAuth;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.query.VideoAuthQueryObject;
import cn.wolfcode.p2p.base.service.ILoginInfoService;
import cn.wolfcode.p2p.base.service.IOrderTimeService;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.service.IVideoAuthService;
import cn.wolfcode.p2p.base.util.AjaxResult;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @RequestMapping("videoAuth")
    @LoginRequird
    public String videoAuditOrder(Model model, @ModelAttribute("qo")VideoAuthQueryObject qo){
        model.addAttribute("pageInfo",videoAuthService.selectAll(qo));
        return "videoAuth/list";
    }

    @LoginRequird
    @RequestMapping("videoAuth_audit")
    @ResponseBody
    public AjaxResult videoAuthAudit(VideoAuth videoAuth){
        AjaxResult result = null;
        try{
            videoAuthService.videoAuthAudit(videoAuth);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
