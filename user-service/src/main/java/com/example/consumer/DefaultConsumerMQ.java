package com.example.consumer;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import java.util.Arrays;

/**
 * @author 20805693
 */
@Component
public class DefaultConsumerMQ extends AbstractRocketConsumer{

    @Override
    public void init() {
        // 设置主题,标签与消费者标题
        super.necessary("TopicTest", "*", "用户服务");


        MessageListenerConcurrently messageListenerConcurrently =  (msgs, context) -> {
            msgs.forEach(msg -> {
                //byte[] body = msg.getBody();
                //Long orderId = NumberUtils.parseNumber(Arrays.toString(body), Long.class);
                //System.out.printf("-------------------------------------consumer message order id %d----------------------------------------", orderId);
                System.out.printf("---------------------------------consumer message boyd ------------------------------------ %s %n", new String(msg.getBody()));
            });
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        };

        //消费者具体执行逻辑
        registerMessageListener(messageListenerConcurrently);


    }

}
