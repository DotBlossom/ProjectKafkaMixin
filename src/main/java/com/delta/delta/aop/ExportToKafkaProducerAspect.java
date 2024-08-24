package com.delta.delta.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ExportToKafkaProducerAspect {


    @AfterReturning(pointcut = "execution(* com.delta.delta.service.*.createComment(..)) || " +
            "execution(* com.com.delta.delta.service.*.likePost(..))",
            returning = "result")
    public void afterReturningExportToKafkaProducerAspect(Object result) {
        if (!(result instanceof Exception)) {
            // 서비스 종료 후 실행할 로직 작성
            System.out.println("Specific service executed successfully. Result: " + result);
        }
    }
}





