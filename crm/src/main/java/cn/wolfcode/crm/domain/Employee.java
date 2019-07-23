package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter@Getter
public class Employee extends BaseDomain {
    private String name; //姓名

    private String password; //密码

    private String email; //邮箱

    private Integer age; //年龄

    private boolean admin; //是否高级管理员

    //关联一方对象
    private Department dept; //部门对象
    //关联多方对象
    private List<Role> roles = new ArrayList<>();

}