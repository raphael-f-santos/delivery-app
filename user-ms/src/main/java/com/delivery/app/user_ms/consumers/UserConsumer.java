package com.delivery.app.user_ms.consumers;

import com.delivery.app.user_ms.dtos.code.CodeReceivedMessageDTO;
import com.delivery.app.user_ms.dtos.order.OrderReceivedMessageDTO;
import com.delivery.app.user_ms.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    private final UserService userService;

    public UserConsumer(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = "${broker.queue.user.name}")
    public void listenerUserQueue(@Payload OrderReceivedMessageDTO message) {
        userService.orderMessageReceiver(message);
    }

    @RabbitListener(queues = "${broker.queue.code.validated.name}")
    public void receiveAndVerifyCodeInfo (@Payload CodeReceivedMessageDTO message) {
        userService.verifyCodeInfo(message);
    }
}