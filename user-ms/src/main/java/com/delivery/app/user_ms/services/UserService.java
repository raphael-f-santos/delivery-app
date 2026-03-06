package com.delivery.app.user_ms.services;

import com.delivery.app.user_ms.configs.code.CodeGenerator;
import com.delivery.app.user_ms.dtos.code.CodeReceivedMessageDTO;
import com.delivery.app.user_ms.dtos.order.OrderMessageDTO;
import com.delivery.app.user_ms.dtos.order.OrderReceivedMessageDTO;
import com.delivery.app.user_ms.dtos.order.OrderRecordDTO;
import com.delivery.app.user_ms.enums.CodeStatus;
import com.delivery.app.user_ms.enums.OrderStatus;
import com.delivery.app.user_ms.models.Code;
import com.delivery.app.user_ms.models.User;
import com.delivery.app.user_ms.producers.UserProducer;
import com.delivery.app.user_ms.repositories.CodeRepository;
import com.delivery.app.user_ms.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CodeRepository codeRepository;

    private final CodeGenerator codeGenerator;
    private final UserProducer userProducer;


    public UserService(UserRepository userRepository, CodeRepository codeRepository, CodeGenerator codeGenerator, UserProducer userProducer){
        this.userRepository = userRepository;
        this.codeRepository = codeRepository;
        this.codeGenerator = codeGenerator;
        this.userProducer = userProducer;
    }

    public void saveCode(OrderRecordDTO orderDto) {

        int code = codeGenerator.generate6DigitCode();

        codeRepository.save(
                new Code(
                        orderDto.user().id(),
                        code,
                        CodeStatus.PENDING,
                        orderDto.id()
                )
        );
    }

    @Transactional
    public void verifyCodeInfo(CodeReceivedMessageDTO message) {

        Code code = codeRepository
                .findByUserIdAndOrderIdAndCodeAndStatus(
                        message.userId(),
                        message.orderId(),
                        message.code(),
                        CodeStatus.CONFIRMED
                )
                .orElseThrow(() ->
                        new IllegalStateException("Invalid code")
                );

        code.setStatus(CodeStatus.USED);
    }

    @Transactional
    public void orderMessageReceiver(OrderReceivedMessageDTO message) {

        Code code = codeRepository
                .findFirstByUserIdAndStatus(message.userId(), CodeStatus.PENDING)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Pending code not found for userId=" + message.userId()
                        )
                );

        code.setOrderId(message.orderId());
        code.setStatus(CodeStatus.CONFIRMED);

        codeRepository.save(code);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void sendOrder(OrderRecordDTO orderDto){

        saveCode(orderDto);

        OrderMessageDTO message = new OrderMessageDTO(
                orderDto.menu(),
                orderDto.quantity(),
                OrderStatus.PENDING,
                orderDto.user().id()
        );

        userProducer.publishMessageOrder(message);
    }
}
