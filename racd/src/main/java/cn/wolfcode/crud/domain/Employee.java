package cn.wolfcode.crud.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Employee extends BaseDomin{

    private String name;

    private String password;

    private String email;

    private Integer age;

    private boolean admin;

    private Department dept;

    private List<Role> role;


}