package com.shiyi.product._03product;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
/**
 *功能描述  worker模式
 * @author 十一
 * @date 2019-07-06 20:00
 */
public class NewTask {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.14.215");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            //队列名，是否持久化，是否只能自己能读取，是否自动删除(队列没有消息并且没有客户端连接），实现死信队列或者延时队列
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
            String message = "tasequeue";
            for(int i=0;i<200;i++){
                channel.basicPublish("", TASK_QUEUE_NAME,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        (message+i).getBytes("UTF-8"));
            }
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}