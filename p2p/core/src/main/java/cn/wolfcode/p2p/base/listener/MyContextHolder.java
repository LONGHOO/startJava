package cn.wolfcode.p2p.base.listener;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 十一
 * @Date: 2019-06-13 21:46
 * @Descrption
 **/
public class MyContextHolder {

    private MyContextHolder(){};

    public static ThreadLocal<HttpServletRequest> holder = new ThreadLocal<>();

    public void set(HttpServletRequest req){
        holder.set(req);
    }

    public HttpServletRequest get(){
        return holder.get();
    }
}
