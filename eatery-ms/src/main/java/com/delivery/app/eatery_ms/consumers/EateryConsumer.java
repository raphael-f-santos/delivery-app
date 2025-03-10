package com.delivery.app.eatery_ms.consumers;

import com.delivery.app.eatery_ms.dtos.OrderRecordDto;
import com.delivery.app.eatery_ms.services.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EateryConsumer {

    private final OrderService orderService;

    public EateryConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = "${broker.queue.eatery.name}")
    public void listenEateryQueue(@Payload OrderRecordDto orderRecordDto) {
        orderService.saveOrder(orderRecordDto);
    }
}
