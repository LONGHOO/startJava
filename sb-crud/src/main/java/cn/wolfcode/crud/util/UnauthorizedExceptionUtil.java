package cn.wolfcode.crud.util;

import com.alibaba.fastjson.JSON;
//import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class UnauthorizedExceptionUtil {

    /*@ExceptionHandler(UnauthorizedException.class)
    public void handler(HttpServletResponse response, HandlerMethod method, UnauthorizedException e)
            throws IOException {
        if (method.getMethod().isAnnotationPresent(ResponseBody.class)) {
            response.setContentType("text/json;charset=UTF-8");
            JSONResult result = new JSONResult();
            result.mark("对不起，您没有权限执行该操作");
            response.getWriter().print(JSON.toJSONString(result));
        } else {
            throw e;
        }
    }*/
}