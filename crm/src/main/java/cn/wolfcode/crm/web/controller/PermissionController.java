package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.util.ResultInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    public String list(@ModelAttribute QueryObject qo, Model model) {
        model.addAttribute("pageInfo", permissionService.query(qo));
        return "permission/list";
    }
    

    @RequestMapping("/delete")
    @RequiresRoles("admin")
    public String delete(Long id) {
        permissionService.delete(id);
        return "redirect:/permission/list.do";
    }
    @RequestMapping("/reload")
    @ResponseBody
    public ResultInfo reload() {
        try {
            permissionService.reload();
            return ResultInfo.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }
}
