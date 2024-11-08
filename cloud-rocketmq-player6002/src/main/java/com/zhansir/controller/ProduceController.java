package com.zhansir.controller;


import com.zhansir.common.resp.ResultData;
import com.zhansir.dto.User;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
@Slf4j
public class ProduceController
{
    @Resource
    private RocketMQTemplate myRocketMqTemplate;

    @GetMapping("/BatchSendUser")
    public ResultData<String> BatchSendUser() {
        // BatchSendUserSync method executed in 355 ms
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserId(i);user.setName("user"+i);user.setAge(new Random().nextInt(100));
            String key = "userKey-" + user.getUserId();

            Message<String> stringMessage = MessageBuilder.withPayload(user.toString())
                    .setHeader("owner", "zhansir")
                    .setHeader("KEYS", key)
                    .build();

            myRocketMqTemplate.syncSend("cloud-batch-sync-topic", stringMessage);
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("BatchSendUserSync method executed in " + duration + " ms");
        return ResultData.success("success");
    }

    @GetMapping("/BatchSendUserWithTag")
    public ResultData<String> BatchSendUserTag() {
        String[] tags = {"A", "B", "C"};

        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserId(i);user.setName("user"+ i);user.setAge(new Random().nextInt(100));

            String key = "userKey-" + user.getUserId();
            String tag = tags[i % tags.length];

            Message<String> stringMessage = MessageBuilder.withPayload(user.toString())
                    .setHeader("KEYS", key)
                    .build();

            myRocketMqTemplate.send("cloud-batch-sync-topic-tag" + ":" + tag, stringMessage);
        }

        return ResultData.success("success");
    }

    @GetMapping("/BatchSendUserAsync")
    public ResultData<String> BatchSendUserAsync() {
        // BatchSendUserAsync method executed in 5 ms
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserId(i);user.setName("user"+ i);user.setAge(new Random().nextInt(100));
            String key = "userKey-" + user.getUserId();

            Message<String> stringMessage = MessageBuilder.withPayload(user.toString())
                    .setHeader("KEYS", key)
                    .build();

            myRocketMqTemplate.asyncSend("cloud-batch-sync-topic-async",
                    stringMessage,
                    new SendCallback() {
                        @Override
                        public void onSuccess(SendResult sendResult) {
                            log.info("Send Success:" + sendResult.toString());
                        }

                        @Override
                        public void onException(Throwable e) {
                            log.error(e.toString());
                        }
                    });
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("BatchSendUserAsync method executed in " + duration + " ms");

        return ResultData.success("success");
    }
}
