package cn.wolfcode.crm.web.filter;

import cn.wolfcode.crm.util.ResultInfo;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 十一
 * @Date: 2019-05-07 20:51
 * @Descrption
 **/
@Component("crmFormAuthenticationFilter")
public class CRMFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().println(JSON.toJSONString(ResultInfo.success()));
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        String msg = "";
        if (e instanceof UnknownAccountException) {
            msg = "账号错误";
        } else if (e instanceof IncorrectCredentialsException) {
            msg = "密码错误";
        } else {
            msg = "账号/密码错误";
        }
        e.printStackTrace();
        response.setContentType("text/json;charset=UTF-8");
        try {
            response.getWriter().print(JSON.toJSON(ResultInfo.fail(msg)));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }
}
