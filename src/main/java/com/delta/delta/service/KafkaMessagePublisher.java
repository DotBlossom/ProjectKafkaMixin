package com.delta.delta.service;

import com.delta.delta.dto.NotificationsDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> template;

    @Value("${notifications.topic-name}")
    String topicName = "";

    public void sendObjectToTopic(Long userId, NotificationsDto dto) {
        try {
            String key = String.valueOf(userId % 3);



            CompletableFuture<SendResult<String, Object>> future =
                    template.send(topicName, key, dto);




            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    RecordMetadata recordMetadata = result.getRecordMetadata();
                    sendLog(dto.toString(), recordMetadata);

                } else {
                    System.out.println("Unable to send message=[" +
                            dto.toString() + "] due to : " + ex.getMessage());
                }
            });

        } catch (Exception ex) {
            System.out.println("ERROR : "+ ex.getMessage());
        }
    }

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future =
                template.send(topicName, message);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                RecordMetadata recordMetadata = result.getRecordMetadata();
                sendLog(message, recordMetadata);
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }

    private static void sendLog(String message, RecordMetadata recordMetadata) {
        log.info("Sent message = {} with offset = {}", message, recordMetadata.offset());
        log.info("Topic Name = {}", recordMetadata.topic());
        log.info("Topic Partition Count = {}", recordMetadata.partition());
    }
}