package com.shiyi.server;

import com.shiyi.server.handler.MyWebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: 十一
 * @Date: 2019-05-23 17:20
 * @Descrption
 **/
public class Main {
    public static void main(String[] args){
        EventLoopGroup group = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group, workGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new MyWebSocketHandler());
            System.out.println("服务器开启等待客户端的链接");
            Channel ch = bootstrap.bind(9999).sync().channel();
            ch.closeFuture().sync();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //优雅的退出程序
            group.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
