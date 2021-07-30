package com.example.consumer;

import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**
 * 消费者接口
 * @author 20805693
 */
public interface consumer {

    /**
     * 初始化消费者
     */
    public abstract void init();

    /**
     * 注册监听
     *
     * @param messageListener
     */
    public void registerMessageListener(MessageListenerConcurrently messageListener);

}
