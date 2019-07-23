package com.shiyi.product._01product;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * @Author: 十一
 * @Date: 2019-07-06 18:55
 * @Descrption
 **/
public class Product {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.14.215");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            //队列名，是否持久化，是否只能自己能读取，是否自动删除(队列没有消息并且没有客户端连接），实现死信队列或者延时队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
