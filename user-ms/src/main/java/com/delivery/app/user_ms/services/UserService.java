package com.delivery.app.user_ms.services;

import com.delivery.app.user_ms.dtos.OrderDto;
import com.delivery.app.user_ms.models.User;
import com.delivery.app.user_ms.producers.UserProducer;
import com.delivery.app.user_ms.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer){
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void sendOrder(OrderDto orderDto){
        userProducer.publishMessageOrder(orderDto);
    }
}
