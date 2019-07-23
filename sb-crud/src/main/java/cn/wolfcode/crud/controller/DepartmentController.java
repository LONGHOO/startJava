package cn.wolfcode.crud.controller;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IDepartmentService;
import cn.wolfcode.crud.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo")QueryObject qo, Model model){
        model.addAttribute("result",departmentService.query(qo));
        return "/department/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONResult delete(Long id){
        JSONResult result = new JSONResult();
        try {
            departmentService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除失败了.");
        }
        return result;
    }
    @RequestMapping("/input")
    public String input(Long id,Model model){
        if (id != null){
            model.addAttribute("entity",departmentService.get(id));
        }
        return "/department/input";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JSONResult saveOrUpdate(Department entity){
        JSONResult result = new JSONResult();
        try {
            departmentService.saveOrUpdate(entity);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了.");
        }
        return result;
    }
}
