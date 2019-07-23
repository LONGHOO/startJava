package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import cn.wolfcode.crm.service.IDictionaryItemService;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.util.ResultInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDictionaryItemService itemService;

    @Autowired
    private IEmployeeService employeeService;

    @RequiresPermissions(value = {"潜在客户列表", "customer:potentialList"}, logical = Logical.OR)
    @RequestMapping("/potentialList")
    public String list(@ModelAttribute("qo") CustomerQueryObject qo, Model model) {
        model.addAttribute("pageInfo", customerService.query(qo));
        model.addAttribute("jobs",itemService.queryItemBySn("job"));
        model.addAttribute("sources",itemService.queryItemBySn("source"));
        model.addAttribute("sellers",employeeService.queryEmpByRoles("Market_Manager","Market"));
        //交流方式
        model.addAttribute("ccts",itemService.queryItemBySn("communicationMethod"));
        return "customer/potential";
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdate(Customer entity) {
        try {
            customerService.saveOrUpdate(entity);
            return ResultInfo.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public ResultInfo updateStatus(Integer status,String customerId ) {
        try {
            customerService.updateStatus(status,customerId);
            return ResultInfo.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }

    @RequiresPermissions(value = {"客户池列表", "customer:poolListList"}, logical = Logical.OR)
    @RequestMapping("/poolList")
    public String poolList(@ModelAttribute("qo") CustomerQueryObject qo, Model model) {
        model.addAttribute("pageInfo", customerService.queryPooled(qo));
        model.addAttribute("sellers",employeeService.queryEmpByRoles("Market_Manager","Market"));
        return "customer/pool_list";
    }
}
