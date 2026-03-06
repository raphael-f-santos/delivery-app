package com.delivery.app.user_ms.producers;

import com.delivery.app.user_ms.dtos.code.CodeValidatedDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CodeProducer {

    private final RabbitTemplate rabbitTemplate;

    public CodeProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${broker.queue.code.validated.name}")
    private String validatedRoutingKey;

    public void sendCodeValidated(Long orderId) {
        rabbitTemplate.convertAndSend(
                validatedRoutingKey,
                new CodeValidatedDTO(orderId)
        );
    }
}
