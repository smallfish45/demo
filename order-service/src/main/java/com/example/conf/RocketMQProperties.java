package com.example.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 20805693
 */
@ConfigurationProperties(prefix = "rocketmq")
public class RocketMQProperties {

    private boolean isEnable = false;
    private String namesrvAddr = "localhost:9876";
    private String groupName = "default";
    private int producerMaxMessageSize = 1024;
    private int producerSendMsgTimeout = 2000;
    private int producerRetryTimesWhenSendFailed = 2;


    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getProducerMaxMessageSize() {
        return producerMaxMessageSize;
    }

    public void setProducerMaxMessageSize(int producerMaxMessageSize) {
        this.producerMaxMessageSize = producerMaxMessageSize;
    }

    public int getProducerSendMsgTimeout() {
        return producerSendMsgTimeout;
    }

    public void setProducerSendMsgTimeout(int producerSendMsgTimeout) {
        this.producerSendMsgTimeout = producerSendMsgTimeout;
    }

    public int getProducerRetryTimesWhenSendFailed() {
        return producerRetryTimesWhenSendFailed;
    }

    public void setProducerRetryTimesWhenSendFailed(int producerRetryTimesWhenSendFailed) {
        this.producerRetryTimesWhenSendFailed = producerRetryTimesWhenSendFailed;
    }

}
