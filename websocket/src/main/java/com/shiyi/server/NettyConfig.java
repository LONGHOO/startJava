package com.shiyi.server;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author: 十一
 * @Date: 2019-05-21 21:13
 * @Descrption
 **/
public class NettyConfig  {

    /**
     *
     * 功能描述: 存储每一个客户端接入进来时的channel对象
     * @auther: 十一
     * @date:   2019-05-21 21:15
     */
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
