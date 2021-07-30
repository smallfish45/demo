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
    private int consumerConsumeThreadMin = 5;
    private int consumerConsumeThreadMax = 30;
    private int consumerConsumeMessageBatchMaxSize = 1;

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

    public int getConsumerConsumeThreadMin() {
        return consumerConsumeThreadMin;
    }

    public void setConsumerConsumeThreadMin(int consumerConsumeThreadMin) {
        this.consumerConsumeThreadMin = consumerConsumeThreadMin;
    }

    public int getConsumerConsumeThreadMax() {
        return consumerConsumeThreadMax;
    }

    public void setConsumerConsumeThreadMax(int consumerConsumeThreadMax) {
        this.consumerConsumeThreadMax = consumerConsumeThreadMax;
    }

    public int getConsumerConsumeMessageBatchMaxSize() {
        return consumerConsumeMessageBatchMaxSize;
    }

    public void setConsumerConsumeMessageBatchMaxSize(int consumerConsumeMessageBatchMaxSize) {
        this.consumerConsumeMessageBatchMaxSize = consumerConsumeMessageBatchMaxSize;
    }
}
