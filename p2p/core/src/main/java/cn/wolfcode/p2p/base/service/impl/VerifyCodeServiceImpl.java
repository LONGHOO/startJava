package cn.wolfcode.p2p.base.service.impl;

import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.service.IVerifyCodeService;
import cn.wolfcode.p2p.base.util.AssertUtil;
import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.base.util.DateUtil;
import cn.wolfcode.p2p.base.util.UserContext;
import cn.wolfcode.p2p.base.vo.VerifyCodeVo;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: 十一
 * @Date: 2019-06-12 20:09
 * @Descrption
 **/
@ConfigurationProperties(prefix = "message")
@Service
@Getter
@Setter
public class VerifyCodeServiceImpl implements IVerifyCodeService {

    private String messageurl;
    private String appkey;

    /**
     *功能描述 发送验证码
     * @author 十一
     * @param username, session
     * @return void
     * @date 2019-06-12 20:11
     */
    @Override
    public void sendVerifyCode(String username) {
        AssertUtil.hasLength(username,"手机号不能为空");
        HttpSession session = UserContext.getSession();
        //判断上次发送验证码的时间
        VerifyCodeVo verifyCodeVo = UserContext.getVerifyCodeInSession();
        //判断是否有短信发送历史
        Map<String,String> map = (Map<String, String>) session.getAttribute("phoneNumberHistory");
        if(null == map){
            //定义用于保存已经发送了的短信集合
            Map<String,String> numberMap = new HashMap<String,String>();
            //将当前的号码保存到历史中
            numberMap.put(username,username);
            session.setAttribute("phoneNumberHistory",numberMap);
            send(username);
        }else{
            String number = map.get(username);
            //如果为null，表示当前号码未发送过短信，执行发送短信操作
            if((null == number) || (null == verifyCodeVo || DateUtil.getSecondsBetween(verifyCodeVo.getSendTime(),new Date())
                    > Constants.VERIFYCODE_VAILD_TIME * 60)){
                send(username);

            }else{
                throw new DisplayException("验证发送过于频繁");
            }
        }

    }

    /**
     *功能描述 发送验证码
     * @author 十一
     * @param [username]
     * @return void
     * @date 2019-06-14 00:08
     */
    private void send(String username) {
        String verifyCode = UUID.randomUUID().toString().substring(0, 4);
        VerifyCodeVo vo = new VerifyCodeVo();
        vo.setPhoneNumber(username);
        vo.setSendTime(new Date());
        vo.setVerifyCode(verifyCode);
        UserContext.setVerifyCodeInSession(vo);
        StringBuffer sb = new StringBuffer();
        String content = "你的验证码是：["+verifyCode+"]，3分钟内有效！";
        System.out.println(content);
        //sendMessage(username,content);
    }

    /**
     *功能描述 判断是否存在验证码对象，判断用户名是否相同，判断验证码是否相同，判断时间是否没有过期
     * @author 十一
     * @param username, verifyCode, session
     * @return boolean
     * @date 2019-06-13 09:53
     */
    @Override
    public boolean checkVerifyCode(String username, String verifyCode) {
        VerifyCodeVo vo = UserContext.getVerifyCodeInSession();
        if(null != vo && vo.getPhoneNumber().equals(username)
            && vo.getVerifyCode().equals(verifyCode)
            && DateUtil.getSecondsBetween(vo.getSendTime(),new Date()) < Constants.VERIFYCODE_VAILD_TIME * 60){
            return true;
        }
        return false;
    }

    /**
     *功能描述 使用httpClient向短信平台发送短信
     * @author 十一
     * @param number;电话号码 content消息内容
     * @return void
     * @date 2019-06-14 14:33
     */
    private void sendMessage(String number ,String content){
        //创建httpClient实例
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //使用post方法发送消息
        StringBuffer sb = new StringBuffer();
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("smsMob", number));
        list.add(new BasicNameValuePair("smsText", content));
        list.add(new BasicNameValuePair("Key", appkey));
        list.add(new BasicNameValuePair("Uid", "shiyi502"));
        try {
            String params = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
            HttpGet httpGet = new HttpGet(messageurl+"?"+params);
             // 响应模型
            CloseableHttpResponse response = null;
            // 由客户端执行(发送)Post请求
            response =client.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            String result = EntityUtils.toString(responseEntity, "UTF-8");
            if(Integer.valueOf(result)<1){
                throw new DisplayException("短信发送失败");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
