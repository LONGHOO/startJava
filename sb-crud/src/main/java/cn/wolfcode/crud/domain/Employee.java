package cn.wolfcode.crud.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class Employee extends BaseDomain {

    private String name;

    private String password;

    private String email;

    private Integer age;

    private Boolean admin;

    //关联one方对象
    private Department dept;

    //关联角色对象,many方对象
    private List<Role> roles = new ArrayList<>();

    public List<Role> getRole(){
        //用额外sql把数据封装到list集合中,然后返回
        return roles;
    }

}