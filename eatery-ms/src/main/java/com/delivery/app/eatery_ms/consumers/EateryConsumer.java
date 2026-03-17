package com.delivery.app.eatery_ms.consumers;

import com.delivery.app.eatery_ms.dtos.code.CodeValidatedDTO;
import com.delivery.app.eatery_ms.dtos.order.OrderRecordDTO;
import com.delivery.app.eatery_ms.enums.OrderStatus;
import com.delivery.app.eatery_ms.models.Order;
import com.delivery.app.eatery_ms.repositories.OrderRepository;
import com.delivery.app.eatery_ms.services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EateryConsumer {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public EateryConsumer(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @RabbitListener(queues = "${broker.queue.eatery.name}")
    public void listenEateryQueue(@Payload OrderRecordDTO orderRecordDto) {
        orderService.saveOrder(orderRecordDto);
    }

    @RabbitListener(queues = "${broker.queue.code.validated.name}")
    @Transactional
    public void handleCodeValidated(CodeValidatedDTO message) {

        System.out.println(message);

        Order order = orderRepository
                .findById(message.orderId())
                .orElseThrow();

        order.setStatus(OrderStatus.DELIVERED);
    }
}
