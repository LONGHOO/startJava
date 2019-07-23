package cn.wolfcode.rbac.web;

import cn.wolfcode.rbac.qo.QueryObject;
import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        model.addAttribute("depts", departmentService.list(qo));
        return "/department/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        departmentService.delete(id);
        return "redirect:/department/list.do";
    }
    @RequestMapping("/input")
    public String input(Long id,Model model) {
        if (id != null) {
            model.addAttribute("dept",departmentService.get(id));
        }
        return "/department/input";
    }


    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Department dept) {
        departmentService.saveOrUpdate(dept);

        return "redirect:/department/list.do";
    }

}
