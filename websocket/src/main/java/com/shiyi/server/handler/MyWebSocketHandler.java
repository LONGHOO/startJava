package com.shiyi.server.handler;

import com.shiyi.server.NettyConfig;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-05-21 21:17
 * @Descrption 接受/处理/响应客户端websocket请求的核心业务类
 **/
public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;
    private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";

    /**
     *
     * 功能描述: 服务器接受客户端请求的核心方法
     * @auther: 十一
     * @date:   2019-05-21 21:22
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //处理客户端向服务器发起的http握手请求的业务
        if(msg instanceof FullHttpRequest){
            handHttpRequest(ctx,(FullHttpRequest) msg);
            //处理客户端发起的websocket业务
        }else if(msg instanceof WebSocketFrame){
            handWebSocketFrame(ctx,(WebSocketFrame)msg);
        }
    }
    /**
     *
     * 功能描述: 处理服务器与客户端之间的websocket业务
     * @auther: 十一
     * @date:   2019-05-21 22:23
     */
    private void handWebSocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame){
        //判断是否是关闭websocket指令
        if(frame instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel()  ,(CloseWebSocketFrame) frame.retain());
        }
        //判断是否是ping指令
        if(frame instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return ;
        }
        //判断是否是二进制消息
        if(!(frame instanceof TextWebSocketFrame)){
            System.out.println("目前不支持二进制数据");
            throw new RuntimeException(this.getClass().getName() + "目前不支持的数据类型");
        }
        //返回应答消息
        //获取客户端向服务器发送的消息
        String message = ((TextWebSocketFrame) frame).text();
        TextWebSocketFrame tsf = new TextWebSocketFrame(new Date().toString()+"..."+ctx.channel().id()
                                                +"==========>"+message);
        //群发消息
        NettyConfig.group.write(message);
    }

    /**
     *
     * 功能描述: 处理http请求的业务
     * @auther: 十一
     * @date:   2019-05-21 21:37
     */
    private void handHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req){
        //如果不成功或者客户端的请求头上的Upgrade不是websocket
        if(!req.decoderResult().isSuccess() || !("websocket".equals(req.headers().get("Upgrade")))){
            sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.BAD_REQUEST));
            return ;
        }
        WebSocketServerHandshakerFactory factory =
                new WebSocketServerHandshakerFactory(WEB_SOCKET_URL,null,false);
        handshaker = factory.newHandshaker(req);
        if(null == handshaker){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            handshaker.handshake(ctx.channel(),req);
        }
    }
    /**
     *
     * 功能描述: 服务器端向客户端发起响应
     * @auther: 十一
     * @date:   2019-05-21 21:42
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response){
        if(response.getStatus().code() != 200){
            ByteBuf bf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(bf);
            bf.release();
        }
        //服务端向客户端发送数据
        ChannelFuture future = ctx.channel().writeAndFlush(response);
        if(response.getStatus().code() != 200){
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }
    /**
     *
     * 功能描述: 客户端和服务器端建立链接的时候调用
     * @auther: 十一
     * @date:   2019-05-21 21:23
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.add(ctx.channel());
        System.out.println("服务器和客户端的链接开启 ....");
    }

    /**
     *
     * 功能描述: 客户端和服务器断开链接后调用
     * @auther: 十一
     * @date:   2019-05-21 21:23
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.remove(ctx.channel());
        System.out.println("服务器和客户端链接关闭....");

    }




    /**
     *
     * 功能描述: 服务器在接受客户端发送消息结束后调用
     * @auther: 十一
     * @date:   2019-05-21 21:25
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     *
     * 功能描述: 工程出现异常的时候调用
     * @auther: 十一
     * @date:   2019-05-21 21:24
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
