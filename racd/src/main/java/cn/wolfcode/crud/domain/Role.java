package cn.wolfcode.crud.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

@Getter
@Setter
@ToString
public class Role  extends BaseDomin{


    private String name;

    private String sn;

    private List<Permission> permissions;

}