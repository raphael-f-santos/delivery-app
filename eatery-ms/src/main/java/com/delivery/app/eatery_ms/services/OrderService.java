package com.delivery.app.eatery_ms.services;

import com.delivery.app.eatery_ms.dtos.OrderRecordDto;
import com.delivery.app.eatery_ms.models.Order;
import com.delivery.app.eatery_ms.models.User;
import com.delivery.app.eatery_ms.repositories.OrderRepository;
import com.delivery.app.eatery_ms.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public void saveOrder(OrderRecordDto orderRecordDto){

        User user = userRepository.findByName(orderRecordDto.user().name()).orElseGet(
                () -> userRepository.save(new User(orderRecordDto.user().name(), orderRecordDto.user().address()))
        );

        Order order = new Order(
                orderRecordDto.menu(),
                orderRecordDto.quantity(),
                orderRecordDto.status(),
                user
        );

        orderRepository.save(order);
    }
}
