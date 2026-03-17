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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){
        var user = new User();
        BeanUtils.copyProperties(userRecordDto, user);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @PostMapping("/send-order")
    public ResponseEntity<String> sendOrder(@RequestBody @Valid OrderRecordDTO orderRecordDto) {
        userService.sendOrder(orderRecordDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Order sent for processing");
    }
}
