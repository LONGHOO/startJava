package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.CustomerTraceHistory;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.service.ICustomerTraceHistoryService;
import cn.wolfcode.crm.service.IDictionaryItemService;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customerTraceHistory")
public class CustomerTraceHistoryController {

    @Autowired
    private ICustomerTraceHistoryService customerTraceHistoryService;
    @Autowired
    private IDictionaryItemService dictionaryItemService;

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdate(CustomerTraceHistory entity) {
        try {
            customerTraceHistoryService.saveOrUpdate(entity);
            return ResultInfo.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }

    @RequestMapping("/editHistoryTrace")
    @ResponseBody
    public ResultInfo editHistoryTrace(CustomerTraceHistory entity) {
        try {
            customerTraceHistoryService.editHistoryTrace(entity);
            return ResultInfo.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") CustomerQueryObject qo, Model model) {
        model.addAttribute("pageInfo",customerTraceHistoryService.query(qo));
        model.addAttribute("traceTypes",dictionaryItemService.queryItemBySn("communicationMethod"));
       return "customerTraceHistory/list";
    }


}
