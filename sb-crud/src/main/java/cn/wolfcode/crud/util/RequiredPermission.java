package cn.wolfcode.crud.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//权限注解只贴在方法上
@Retention(RetentionPolicy.RUNTIME)//存活运行时候
public @interface RequiredPermission {
    String[] value();
}
