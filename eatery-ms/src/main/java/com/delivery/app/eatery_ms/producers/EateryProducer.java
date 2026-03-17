package com.delivery.app.eatery_ms.producers;

import com.delivery.app.eatery_ms.dtos.code.CodeMessageDTO;
import com.delivery.app.eatery_ms.dtos.order.OrderMessageDTO;
import com.delivery.app.eatery_ms.models.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EateryProducer {

    private final RabbitTemplate rabbitTemplate;

    public EateryProducer(RabbitTemplate rabbitTemplate) {this.rabbitTemplate = rabbitTemplate;}

    @Value("${broker.queue.user.name}")
    private String notifyUserMsRoutingKey;

    @Value("${broker.queue.code.validated.name}")
    private String codeNotifyUserMsRoutingKey;

    public void notifyUserMs(Order order) {

        OrderMessageDTO message =
                new OrderMessageDTO(
                        order.getId(),
                        order.getUserId()
                );

        rabbitTemplate.convertAndSend(
                "",
                notifyUserMsRoutingKey,
                message
        );
    }

    public void codeNotifyUserMs(CodeMessageDTO codeMessageDTO) {
        rabbitTemplate.convertAndSend(
                "",
                codeNotifyUserMsRoutingKey,
                codeMessageDTO
        );
    }
}
