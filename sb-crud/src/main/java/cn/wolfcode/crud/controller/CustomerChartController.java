package cn.wolfcode.crud.controller;

import cn.wolfcode.crud.query.CustomerChartQueryObject;
import cn.wolfcode.crud.service.ICustomerChartService;
import com.alibaba.fastjson.JSON;
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
@RequestMapping("/customerChart")
public class CustomerChartController {

    @Autowired
    private ICustomerChartService customerChartService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo")CustomerChartQueryObject qo, Model model){
        model.addAttribute("charts",customerChartService.listCustomerCharts(qo));
        return "chart/list";
    }

    @RequestMapping("/chartByBar")
    public String chartByBar(@ModelAttribute("qo")CustomerChartQueryObject qo, Model model){
        model.addAttribute("groupTypeName",qo.getGroupTypeName());

        List<Map<String,Object>> list = customerChartService.listCustomerCharts(qo);
        //将查询结果中的数据获取到封装到一个集合
        List<String> groupTypes = new ArrayList<>();
        List<Object> numbers = new ArrayList<>();

        for (Map<String,Object> map : list){
            groupTypes.add(map.get("groupType").toString());
            numbers.add(map.get("number"));
        }
        //System.out.println(groupTypes);
        //将集合转换成json字符串响应给页面
        model.addAttribute("groupTypes", JSON.toJSONString(groupTypes));
        model.addAttribute("numbers", JSON.toJSONString(numbers));
        return "chart/chartByBar";
    }

    @RequestMapping("/chartByPie")
    public String chartByPie(@ModelAttribute("qo")CustomerChartQueryObject qo, Model model){
        model.addAttribute("groupTypeName",qo.getGroupTypeName());

        List<Map<String,Object>> list = customerChartService.listCustomerCharts(qo);
        //将查询结果中的数据获取到封装到一个集合
        List<String> groupTypes = new ArrayList<>();
        List<Map<String,Object>> datas = new ArrayList<>();

        for (Map<String,Object> map : list){
            groupTypes.add(map.get("groupType").toString());

            Map<String,Object> data = new HashMap<>();
            data.put("name",map.get("groupType"));
            data.put("value",map.get("number"));
            datas.add(data);
        }
        //System.out.println(groupTypes);
        //将集合转换成json字符串响应给页面
        model.addAttribute("groupTypes", JSON.toJSONString(groupTypes));
        model.addAttribute("datas", JSON.toJSONString(datas));
        return "chart/chartByPie";
    }

}
