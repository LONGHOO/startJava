package cn.wolfcode.rbac.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class Employee extends BaseDomain {

    private String name;

    private String password;

    private String email;

    private boolean admin;

    private Integer age;

    private Department dept;

    private List<Role> roles = new ArrayList<>();

}