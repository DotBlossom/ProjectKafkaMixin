package com.delta.delta.aop;

import com.delta.delta.controller.PostLikeController;
import com.delta.delta.dto.NotificationsDto;
import com.delta.delta.entity.PostLike;
import com.delta.delta.entity.User;
import com.delta.delta.service.KafkaMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class ExportToKafkaProducerAspect {

    private final KafkaMessagePublisher kafkaMessagePublisher;


    @AfterReturning(pointcut = "execution(* com.delta.delta.service.*.likePost(..)) || " +
            "execution(* com.delta.delta.service.*.createUser(..))",
            returning = "result")
    public void afterReturningExportToKafkaProducerAspect(Object result) {
        if (!(result instanceof Exception)) {

            String resType = result.getClass().getSimpleName();
            if (result instanceof User userInstance) {
                Long userId = userInstance.getUserId(); // userId 값 가져오기
                System.out.println("Specific service executed successfully. Result: " + userId);

            } else if (result instanceof PostLike postLikeInstance) {
                // sender가 post를 좋아함. dto interface?
                Long postId = postLikeInstance.getPost().getPostId();
                Long senderId = postLikeInstance.getUser().getUserId();
                Long receiverId = postLikeInstance.getPost().getUser().getUserId();

                NotificationsDto dto = new NotificationsDto();
                dto.setPostId(postId);
                dto.setSenderId(senderId);
                dto.setReceiverId(receiverId);
                dto.setEventType("postLike");


                System.out.println("Specific service executed successfully. Result: " + dto.getReceiverId());
                kafkaMessagePublisher.sendObjectToTopic(dto.getReceiverId(), dto);

            }

            //kafkaMessagePublisher.sendObjectToTopic(, NotificationsDto);
            System.out.println("Specific service executed successfully. Result: " + resType);

        }
    }
}





