package cn.wolfcode.p2p.base.mgrsite.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.util.AjaxResult;
import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.bussniss.domain.BidRequestAuditHistory;
import cn.wolfcode.p2p.bussniss.query.BidRequestQueryObject;
import cn.wolfcode.p2p.bussniss.service.IBidRequestAuditHistoryService;
import cn.wolfcode.p2p.bussniss.service.IBidRequsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-06-19 19:52
 * @Descrption
 **/
@Controller
public class BidRequestAuditHistoryController {

    @Autowired
    private IBidRequestAuditHistoryService bidRequestAuditHistoryService;

    @Autowired
    private IBidRequsetService bidRequsetService;

    /**
     *功能描述 查询出所有的审核信息
     * @author 十一
     * @param model, qo
     * @return java.lang.String
     * @date 2019-06-19 20:15
     */
    @LoginRequird
    @RequestMapping("bidrequest_publishaudit_list")
    public String publisAuditList(Model model, @ModelAttribute("qo") BidRequestQueryObject qo){
        qo.setState(Constants.BIDREQUEST_STATE_APPLY);
        model.addAttribute("pageInfo",bidRequsetService.query(qo));
        return "bidrequest/publish_audit";
    }

    /**
     *功能描述 发标前审核
     * @author 十一
     * @return
     * @date 2019-06-19 20:19
     */
    @LoginRequird
    @RequestMapping("bidrequest_publishaudit")
    @ResponseBody
    public AjaxResult bidrequestPublishaudit(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishTime,Long id, Integer state,
                                             String remark){
        AjaxResult result = null;
        try{
            bidRequestAuditHistoryService.bidrequestPublishaudit(id, state, publishTime, remark);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

    /**
     *功能描述 满标一审列表
     * @author 十一
     * @param qo
     * @return cn.wolfcode.p2p.base.util.AjaxResult
     * @date 2019-06-21 21:53
     */
    @LoginRequird
    @RequestMapping("bidrequest_audit1_list")
    public String bidrequestAudit1List(Model model,@ModelAttribute("qo") BidRequestQueryObject qo){
        qo.setState(Constants.BIDREQUEST_STATE_APPROVE_PENDING_1);
        model.addAttribute("pageInfo",bidRequsetService.query(qo));

        return "bidrequest/audit1";
    }

    /**
     *功能描述 满标一审
     * @author 十一
     * @return cn.wolfcode.p2p.base.util.AjaxResult
     * @date 2019-06-21 22:02
     */
    @LoginRequird
    @RequestMapping("bidrequest_audit1")
    @ResponseBody
    public AjaxResult bidrequestAudit1(Long id,int state,String remark){
        AjaxResult result = null;
        try{
            bidRequsetService.bidrequestAudit1(id, state, remark);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }


    /**
     *功能描述 满标二审列表
     * @author 十一
     * @param qo
     * @return cn.wolfcode.p2p.base.util.AjaxResult
     * @date 2019-06-21 21:53
     */
    @LoginRequird
    @RequestMapping("bidrequest_audit2_list")
    public String bidrequestAudit2List(Model model,@ModelAttribute("qo") BidRequestQueryObject qo){
        qo.setState(Constants.BIDREQUEST_STATE_APPROVE_PENDING_2);
        model.addAttribute("pageInfo",bidRequsetService.query(qo));

        return "bidrequest/audit2";
    }

    /**
     *功能描述 满标二审
     * @author 十一
     * @return cn.wolfcode.p2p.base.util.AjaxResult
     * @date 2019-06-21 22:02
     */
    @LoginRequird
    @RequestMapping("bidrequest_audit2")
    @ResponseBody
    public AjaxResult bidrequestAudit2(Long id,int state,String remark){
        AjaxResult result = null;
        try{
            bidRequsetService.bidrequestAudit2(id, state, remark);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
