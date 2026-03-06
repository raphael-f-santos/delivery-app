package com.delivery.app.user_ms.controllers;

import com.delivery.app.user_ms.dtos.order.OrderRecordDTO;
import com.delivery.app.user_ms.dtos.user.UserRecordDto;
import com.delivery.app.user_ms.models.User;
import com.delivery.app.user_ms.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){
        var user = new User();
        BeanUtils.copyProperties(userRecordDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PostMapping("/send-order")
    public ResponseEntity<OrderRecordDTO> sendOrder(@RequestBody @Valid OrderRecordDTO orderRecordDto) {
        userService.sendOrder(orderRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRecordDto);
    }
}
