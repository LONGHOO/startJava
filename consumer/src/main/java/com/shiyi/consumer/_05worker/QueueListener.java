package com.shiyi.consumer._05worker;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class QueueListener {
    @RabbitListener(queuesToDeclare = @Queue("boot_queue"))
    public void receiveMsg(String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        System.out.println("收到消息:"+msg);
        //手动发送签收ack
        channel.basicAck(deliveryTag,false);
    }
}
