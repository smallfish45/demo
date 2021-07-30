package com.example.conf;

import com.example.consumer.AbstractRocketConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

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
    private ApplicationContext applicationContext;

    @Resource
    private RocketMQProperties properties;

    private final Logger LOGGER = LoggerFactory.getLogger(RocketMQConfiguation.class);

    /**
     * SpringBoot启动时加载所有消费者
     */
    @PostConstruct
    public void initConsumer() {

        Map<String, AbstractRocketConsumer> consumers = applicationContext.getBeansOfType(AbstractRocketConsumer.class);

        if (consumers.size() == 0) {
            LOGGER.info("init rocket consumer 0");
        }

        consumers.forEach((key, consumer) -> {
            consumer.init();
            createConsumer(consumer);
            LOGGER.info("-------------init success consumer title {} , toipcs {} , tags {}------------------", consumer.getConsumerTitel(), consumer.getTopics(), consumer.getTags());
        });

    }

    /**
     * 通过消费者信息创建消费者
     *
     * @param
     */
    public void createConsumer(AbstractRocketConsumer arc) {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(this.properties.getGroupName());
        consumer.setNamesrvAddr(this.properties.getNamesrvAddr());
        consumer.setConsumeThreadMin(this.properties.getConsumerConsumeThreadMin());
        consumer.setConsumeThreadMax(this.properties.getConsumerConsumeThreadMax());
        consumer.registerMessageListener(arc.getMessageListener());
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        // consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        /**
         * 设置消费模型，集群还是广播，默认为集群
         */
        // consumer.setMessageModel(MessageModel.CLUSTERING);

        /**
         * 设置一次消费消息的条数，默认为1条
         */
        consumer.setConsumeMessageBatchMaxSize(this.properties.getConsumerConsumeMessageBatchMaxSize());
        try {
            consumer.subscribe(arc.getTopics(), arc.getTags());
            consumer.start();
            arc.setMqPushConsumer(consumer);
        } catch (MQClientException e) {
            LOGGER.error("info consumer title {}", arc.getConsumerTitel(), e);
        }

    }

}
