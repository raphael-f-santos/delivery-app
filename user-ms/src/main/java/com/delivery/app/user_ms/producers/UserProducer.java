package com.delivery.app.user_ms.producers;

import com.delivery.app.user_ms.dtos.OrderDto;
import com.delivery.app.user_ms.models.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${broker.queue.eatery.name}")
    private String routingKey;

    public void publishMessageOrder(OrderDto orderDto){
        rabbitTemplate.convertAndSend("", routingKey, orderDto);
    }
}
