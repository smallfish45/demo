package com.example.conf;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 20805693
 */
@Configuration
@EnableConfigurationProperties({ RocketMQProperties.class })
@ConditionalOnProperty(prefix = "rocketmq", value = "isEnable", havingValue = "true")
public class RocketMQConfiguation {

    @Resource
    private RocketMQProperties properties;

    private final Logger LOGGER = LoggerFactory.getLogger(RocketMQConfiguation.class);

    /**
     * 注入一个默认的消费者
     * @return
     * @throws MQClientException
     */
    @Bean
    public DefaultMQProducer getRocketMQProducer() throws MQClientException {

        if (StringUtils.isEmpty(properties.getGroupName())) {
            throw new MQClientException(-1, "groupName is blank");
        }

        if (StringUtils.isEmpty(properties.getNamesrvAddr())) {
            throw new MQClientException(-1, "nameServerAddr is blank");
        }
        DefaultMQProducer producer = new DefaultMQProducer(properties.getGroupName());
        producer.setNamesrvAddr(properties.getNamesrvAddr());

        // producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");

        // 如果需要同一个jvm中不同的producer往不同的mq集群发送消息，需要设置不同的instanceName
        // producer.setInstanceName(instanceName);
        producer.setMaxMessageSize(properties.getProducerMaxMessageSize());
        producer.setSendMsgTimeout(properties.getProducerSendMsgTimeout());

        // 如果发送消息失败，设置重试次数，默认为2次
        producer.setRetryTimesWhenSendFailed(properties.getProducerRetryTimesWhenSendFailed());

        try {
            producer.start();
            LOGGER.info("producer is start ! groupName:{},namesrvAddr:{}", properties.getGroupName(), properties.getNamesrvAddr());
        } catch (MQClientException e) {
            LOGGER.error("producer is error {}", e.getMessage(), e);
            throw e;
        }
        return producer;
    }


}
