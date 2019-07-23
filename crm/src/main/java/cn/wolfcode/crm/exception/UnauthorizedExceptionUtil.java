package cn.wolfcode.crm.exception;

import cn.wolfcode.crm.util.ResultInfo;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author minger
 */
@ControllerAdvice
public class UnauthorizedExceptionUtil {

    @ExceptionHandler(UnauthorizedException.class)
    public void exceptionHandle(HttpServletResponse response, HandlerMethod method,UnauthorizedException e) throws IOException {
        if(method.getMethod().isAnnotationPresent(ResponseBody.class)){
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().print(JSON.toJSON(ResultInfo.fail("您没有当前操作的权限")));
        }else{
            throw e;
        }
    }
}
