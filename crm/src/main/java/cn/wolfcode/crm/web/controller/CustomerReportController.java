package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.ReportQueryObject;
import cn.wolfcode.crm.service.ICustomerReportService;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customerReport")
public class CustomerReportController {

    @Autowired
    private ICustomerReportService service;

    @RequiresPermissions(value = {"报表列表", "customer:reportList"}, logical = Logical.OR)
    @RequestMapping("/reportList")
    public String poolList(@ModelAttribute("qo") ReportQueryObject qo, Model model) {
       model.addAttribute("pageInfo",service.queryForQueryObject(qo));
        return "report/customerReport";
    }

    @RequiresPermissions(value = {"报表柱状图", "customer:reportBar"}, logical = Logical.OR)
    @RequestMapping("/chartByBar")
    public String chartByBar(@ModelAttribute("qo") ReportQueryObject qo, Model model) {
        List<Map<String, Object>> maps = service.queryForQueryObject(qo);
        List<Object> valueList = new ArrayList<>();
        List<String> keyList = new ArrayList<>();
        for(Map<String,Object> map:maps){
            valueList.add(map.get("number"));
            keyList.add(map.get("groupType").toString());
        }
        model.addAttribute("valueList", JSON.toJSONString(valueList));
        model.addAttribute("keyList", JSON.toJSONString(keyList));
        return "report/customerReport_bar";
    }

    @RequiresPermissions(value = {"报表饼状图", "customer:reportPie"}, logical = Logical.OR)
    @RequestMapping("/chartByPie")
    public String chartByPie(@ModelAttribute("qo") ReportQueryObject qo, Model model) {
        List<Map<String, Object>> maps = service.queryForQueryObject(qo);
        List<String> keyList = new ArrayList<>();
        List<Map<String,Object>> datas = new ArrayList<>();
        for(Map<String,Object> map:maps){
            keyList.add(map.get("groupType").toString());
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("value",map.get("number"));
            data.put("name",map.get("groupType"));
            datas.add(data);
        }
        model.addAttribute("datas", JSON.toJSONString(datas));
        model.addAttribute("keyList", JSON.toJSONString(keyList));
        return "report/customerReport_pie";
    }

}
