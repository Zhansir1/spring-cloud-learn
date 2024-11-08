package com.zhansir.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RocketMQMessageListener(consumerGroup = "cloud-batch-sync-topic-listener2",
        topic = "cloud-batch-sync-topic-tag",
        selectorExpression = "A || B",
        selectorType = SelectorType.TAG)
public class ConsumerListener2 implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt message) {
        String messageBody = new String(message.getBody());
        String keys = message.getKeys();
        String tags = message.getTags();
        Map<String, String> properties = message.getProperties();
        log.info("Received message: " + messageBody + "  Message key: " + keys + "  Message tags: " + tags + "  Message properties: " + properties);
    }
}
