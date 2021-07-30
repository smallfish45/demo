package com.example.consumer;

import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**
 * @author 20805693
 */
public abstract class AbstractRocketConsumer implements consumer {

    private String topics;
    private String tags;
    private MessageListenerConcurrently messageListener;
    private String consumerTitel;
    private MQPushConsumer mqPushConsumer;

    public AbstractRocketConsumer() {
    }

    /**
     * 必要的信息
     *
     * @param topics
     * @param tags
     * @param consumerTitel
     */
    public void necessary(String topics, String tags, String consumerTitel) {
        this.topics = topics;
        this.tags = tags;
        this.consumerTitel = consumerTitel;
    }

    @Override
    public abstract void init();

    @Override
    public void registerMessageListener(MessageListenerConcurrently messageListener) {
        this.messageListener = messageListener;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public MessageListenerConcurrently getMessageListener() {
        return messageListener;
    }

    public void setMessageListener(MessageListenerConcurrently messageListener) {
        this.messageListener = messageListener;
    }

    public String getConsumerTitel() {
        return consumerTitel;
    }

    public void setConsumerTitel(String consumerTitel) {
        this.consumerTitel = consumerTitel;
    }

    public MQPushConsumer getMqPushConsumer() {
        return mqPushConsumer;
    }

    public void setMqPushConsumer(MQPushConsumer mqPushConsumer) {
        this.mqPushConsumer = mqPushConsumer;
    }
}
