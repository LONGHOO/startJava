package cn.wolfcode.crud.controller;

import cn.wolfcode.crud.domain.Customer;
import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.query.PotentialCustomerQueryObject;
import cn.wolfcode.crud.service.ICustomerService;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private ISystemdictionaryitemService systemdictionaryitemService;

    //@RequiresPermissions(value={"潜在客户列表","customer:list"},logical = Logical.OR)
    @RequestMapping("/potentialList")
    public String list(@ModelAttribute("qo")PotentialCustomerQueryObject qo, Model model){
        qo.setStatus(Customer.STATUS_POTENTLAL);
        //用户是超管或者经理才能查所有客户------不是就只能查自己
        /*if (!(SecurityUtils.getSubject().hasRole("admin") ||
                SecurityUtils.getSubject().hasRole("Market_Manager"))){
            Employee currentEmp = UserContext.getCurrentEmp();//(Employee) SecurityUtils.getSubject().getPrincipal();
            qo.setSellerId(currentEmp.getId());
        }*/
        model.addAttribute("result",customerService.query(qo));

        //查询所有市场人员
        //model.addAttribute("sellers",employeeService.listEmployeeByRoleSns("Market","Market_Manager"));
        model.addAttribute("sellers",employeeService.listEmployeeByRoleSns("Market","Market_Manager"));

        //查询所有的职业
        model.addAttribute("jobs",systemdictionaryitemService.listItemByDicSn("job"));
        //查询所有的来源
        model.addAttribute("sources",systemdictionaryitemService.listItemByDicSn("source"));
        //查询所有交流方式
        model.addAttribute("types",systemdictionaryitemService.listItemByDicSn("communicationMethod"));
        return "/customer/list";
    }

    //@RequiresPermissions(value={"潜在客户删除","customer:delete"},logical = Logical.OR)
    @RequestMapping("/delete")
    @ResponseBody
    public JSONResult delete(Long id){
        JSONResult result = new JSONResult();
        try {
            customerService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除失败了.");
        }
        return result;
    }

    //@RequiresPermissions(value={"潜在客户新增和修改","customer:saveOrUpdate"},logical = Logical.OR)
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JSONResult saveOrUpdate(Customer entity){
        JSONResult result = new JSONResult();
        try {
            customerService.saveOrUpdate(entity);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了.");
        }
        return result;
    }
    //@RequiresPermissions(value={"潜在客户修改状态","customer:updateStatus"},logical = Logical.OR)
    @RequestMapping("/updateStatus")
    @ResponseBody
    public JSONResult updateStatus(Long cid,Integer status){
        JSONResult result = new JSONResult();
        try {
            customerService.updateStatus(cid,status);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了.");
        }
        return result;
    }

    //@RequiresPermissions(value={"客户池列表","customer:poolList"},logical = Logical.OR)
    @RequestMapping("/poolList")
    public String poolList(@ModelAttribute("qo")PotentialCustomerQueryObject qo, Model model){
        qo.setStatus(Customer.STATUS_POOLED);
        model.addAttribute("result",customerService.query(qo));
        //查询所有市场人员
        //model.addAttribute("sellers",employeeService.listEmployeeByRoleSns("Market","Market_Manager"));
        model.addAttribute("sellers",employeeService.listEmployeeByRoleSns("Market","Market_Manager"));
        //查询所有的职业
        model.addAttribute("jobs",systemdictionaryitemService.listItemByDicSn("job"));
        //查询所有的来源
        model.addAttribute("sources",systemdictionaryitemService.listItemByDicSn("source"));
        return "/customer/poolList";
    }
}
