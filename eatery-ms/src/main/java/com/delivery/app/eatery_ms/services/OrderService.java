package com.delivery.app.eatery_ms.services;

import com.delivery.app.eatery_ms.dtos.order.OrderRecordDTO;
import com.delivery.app.eatery_ms.enums.OrderStatus;
import com.delivery.app.eatery_ms.models.Order;
import com.delivery.app.eatery_ms.producers.EateryProducer;
import com.delivery.app.eatery_ms.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository, EateryProducer eateryProducer) {
        this.orderRepository = orderRepository;
        this.eateryProducer = eateryProducer;
    }

    private final EateryProducer eateryProducer;

    public void saveOrder(OrderRecordDTO orderRecordDto){

        Order order = new Order(
                orderRecordDto.menu(),
                orderRecordDto.quantity(),
                orderRecordDto.status(),
                orderRecordDto.userId()
        );

        Order saved = orderRepository.save(order);

        eateryProducer.notifyUserMs(saved);
    }
}
