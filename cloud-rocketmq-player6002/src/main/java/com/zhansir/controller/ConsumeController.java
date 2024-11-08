package com.zhansir.controller;

import com.zhansir.common.resp.ResultData;
import com.zhansir.config.ProducerConfig;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.AllocateMessageQueueStrategy;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ConsumeController {
    @Resource
    private ProducerConfig producerConfig;

    @GetMapping("/ConsumeBatchUser")
    public ResultData<String> ConsumeBatchUser()
    {
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("cloud-pull-consumer");
        consumer.setNamesrvAddr(producerConfig.getNameServer());
        try {
            consumer.start();
            consumer.setPullBatchSize(100);

            Collection<MessageQueue> messageQueues = consumer.fetchMessageQueues("cloud-batch-sync-topic");
            for (MessageQueue messageQueue : messageQueues) {
                consumer.assign(Collections.singletonList(messageQueue));
                consumer.seek(messageQueue, 0);
                List<MessageExt> extList = consumer.poll();
                printMsg(extList);

            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            consumer.shutdown();
        }
        return ResultData.success("success");
    }

    @GetMapping("/ConsumeBatchUserOffset")
    public ResultData<String> ConsumeBatchUserOffset(@RequestParam("offset") int offset)
    {
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("cloud-pull-consumer");
        consumer.setNamesrvAddr(producerConfig.getNameServer());
        try {
            consumer.start();
            consumer.setPullBatchSize(100);

            Collection<MessageQueue> messageQueues = consumer.fetchMessageQueues("cloud-batch-sync-topic");
            for (MessageQueue messageQueue : messageQueues) {
                int queueId = messageQueue.getQueueId();
                if (queueId >= 2) {
                    consumer.assign(Collections.singletonList(messageQueue));
                    consumer.seek(messageQueue, offset);
                    List<MessageExt> extList = consumer.poll();
                    printMsg(extList);
                }
                else {
                    consumer.assign(Collections.singletonList(messageQueue));
                    consumer.seek(messageQueue, 0);
                    List<MessageExt> extList = consumer.poll();
                    printMsg(extList);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            consumer.shutdown();
        }
        return ResultData.success("success");
    }

    private void printMsg(List<MessageExt> extList) {
        for (MessageExt message : extList) {
            int msgQueueId = message.getQueueId();
            String messageBody = new String(message.getBody());
            long queueOffset = message.getQueueOffset();
            String keys = message.getKeys();
            String tags = message.getTags();
            Map<String, String> properties = message.getProperties();
            log.info("Queue ID: " + msgQueueId + ", Offset: " + queueOffset + ", Key: " + keys + ", Tags: " + tags
                    + ", Properties: " + properties + ", Body: " + messageBody);
        }
    }
}
