package com.shiyi.consumer._02receiver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @Author: 十一
 * @Date: 2019-07-06 18:55
 * @Descrption
 **/
public class Receiver {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.14.215");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //队列名，是否持久化，是否只能自己能读取，是否自动删除(队列没有消息并且没有客户端连接），实现死信队列或者延时队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            int i = 1/0;
            //手动发送ack，如果失败，则重复执行
            //channel.basicAck(delivery.getEnvelope().getDeliveryTag(),true);
        };
        //队列名，自动发送ack，处理消息回调，其他回调
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
