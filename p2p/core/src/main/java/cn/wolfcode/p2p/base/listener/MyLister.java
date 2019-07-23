package cn.wolfcode.p2p.base.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 十一
 * @Date: 2019-06-13 21:43
 * @Descrption
 **/
public class MyLister implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        MyContextHolder.holder.set((HttpServletRequest)sre.getServletRequest());
    }
}
