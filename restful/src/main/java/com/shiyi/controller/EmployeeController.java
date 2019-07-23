package com.shiyi.controller;

import com.shiyi.domain.Employee;
import com.shiyi.domain.Salary;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-05-16 18:43
 * @Descrption
 **/
@RestController
@RequestMapping("employees")
public class EmployeeController {

    /**
     *
     * 功能描述:
     * @param:  根据id获取记录
     * @return: 
     * @auther: 十一
     * @date:    
     */
    @RequestMapping(value = "{id}",method = RequestMethod.POST)
    public Object getEmployee(@PathVariable("id") Long id){
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName("zhangsan");
        return emp;
    }

    /**
     *
     * 功能描述:
     * @param:  根据id删除记录
     * @return: 
     * @auther: 十一
     * @date:    
     */
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public Object deleteEmployee(@PathVariable("id") Long id){
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName("删除的id");
        return emp;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Object updateEmployee(@PathVariable("id") Long id){
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName("更新的id");
        return emp;
    }


    /**
     *
     * 功能描述:
     * @param:  获取员工某个月的薪资
     * @return:
     * @auther: 十一
     * @date:
     */
    @RequestMapping(value = "{id}/salarys/{date}",method = RequestMethod.GET)
    public Object salarysByMonty(@PathVariable("id") Long id,@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM") Date date){
        Salary salary = new Salary();
        salary.setDate(date);
        salary.setId(id);
        salary.setEmployeeId(id);
        salary.setMoney(BigDecimal.TEN);
        Salary asdfas = new Salary();
        return salary;
    }


    /**
     *
     * 功能描述:
     * @param:  使用params要求输入指定的参数
     * @return: 
     * @auther: 十一
     * @date:    
     */
    @RequestMapping(value = "{id}",method = RequestMethod.GET,params = "name=admin")
    public Object params(@PathVariable("id") Long id){
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName("获取到的id");
        return emp;
    }

    /**
     *
     * 功能描述:
     * @param:  使用heads要求输入指定的参数
     * @return:
     * @auther: 十一
     * @date:
     */
    @RequestMapping(value = "heads",headers = {"content-type=text/xml"})
    public Object heads(){
        Employee emp = new Employee();
        emp.setId(2L);
        emp.setName("heads");
        return emp;
    }

    /**
     *
     * 功能描述:
     * @param:  使用params要求输入指定的参数
     * @return:
     * @auther: 十一
     * @date:
     */
    @RequestMapping(value = "consumes",consumes = {"application/json"})
    public Employee conmuse(){
        Employee emp = new Employee();
        emp.setId(2L);
        emp.setName("consumes");
        return emp;
    }
    /**
     *
     * 功能描述:
     * @param:  使用params要求输入指定的参数
     * @return:
     * @auther: 十一
     * @date:
     */
    @RequestMapping(value = "produces",produces = {"application/json"})
    public Employee produces(){
        Employee emp = new Employee();
        emp.setId(2L);
        emp.setName("produces");
        Employee employee = new Employee();
        return emp;
    }
   /**
     *
     * 功能描述:
     * @param:  requestbody
     * @return:
     * @auther: 十一
     * @date:
     */
    @RequestMapping(value = "requestbody",method = RequestMethod.GET)
    public Object requestbodyByJson(@RequestBody Employee employee){
        return employee;
    }

    /**
     *
     * 功能描述:
     * @param:  requestbody
     * @return:
     * @auther: 十一
     * @date:
     */
    @RequestMapping(value = "requestbodyByXMl")
    public Employee requestbodyByXMl(@RequestBody Employee employee){
        return employee;
    }

    @RequestMapping(value = "formPut",method = RequestMethod.PUT)
    public Employee formPut(Employee employee){
        return employee;
    }



}
