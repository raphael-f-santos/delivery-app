package com.delivery.app.eatery_ms.controllers;

import com.delivery.app.eatery_ms.dtos.code.CodeMessageDTO;
import com.delivery.app.eatery_ms.producers.EateryProducer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class EateryController {

    private final EateryProducer eateryProducer;

    public EateryController(EateryProducer eateryProducer) {this.eateryProducer = eateryProducer;}

    @PostMapping("/code")
    public ResponseEntity<CodeMessageDTO> confirmOrder(@RequestBody @Valid CodeMessageDTO codeMessageDto){
        eateryProducer.verifyCodeInfo(codeMessageDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(codeMessageDto);
    }
}
