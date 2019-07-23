package cn.wolfcode.crud.controller;

import cn.wolfcode.crud.domain.Customer;
import cn.wolfcode.crud.domain.Customertracehistory;
import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.query.PotentialCustomerQueryObject;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.ICustomerService;
import cn.wolfcode.crud.service.ICustomertracehistoryService;
import cn.wolfcode.crud.service.IEmployeeService;
import cn.wolfcode.crud.service.ISystemdictionaryitemService;
import cn.wolfcode.crud.util.JSONResult;
import cn.wolfcode.crud.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customertracehistory")
public class CustomertracehistoryController {

    @Autowired
    private ICustomertracehistoryService customertracehistoryService;
    @Autowired
    private ISystemdictionaryitemService systemdictionaryitemService;

    //@RequiresPermissions(value={"跟进历史列表","customertracehistory:list"},logical = Logical.OR)
    @RequestMapping("/list")
    public String list(@ModelAttribute("qo")QueryObject qo, Model model){
        model.addAttribute("result",customertracehistoryService.query(qo));
        //查询所有明细
        model.addAttribute("types",systemdictionaryitemService.listItemByDicSn("communicationMethod"));
        return "/customertracehistory/list";
    }

    //@RequiresPermissions(value={"跟进历史新增和修改","customertracehistory:saveOrUpdate"},logical = Logical.OR)
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JSONResult saveOrUpdate(Customertracehistory entity){
        JSONResult result = new JSONResult();
        try {
            customertracehistoryService.saveOrUpdate(entity);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了.");
        }
        return result;
    }

}
