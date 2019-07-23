package cn.wolfcode.crud.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Role extends BaseDomain{

    private String name;

    private String sn;

    //关联角色对象,many方对象
    List<Permission> permissions = new ArrayList<>();
}