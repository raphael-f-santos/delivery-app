package com.delivery.app.user_ms.services;

import com.delivery.app.user_ms.dtos.code.CodeReceivedMessageDTO;
import com.delivery.app.user_ms.enums.CodeStatus;
import com.delivery.app.user_ms.models.Code;
import com.delivery.app.user_ms.producers.CodeProducer;
import com.delivery.app.user_ms.repositories.CodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

    private final CodeRepository codeRepository;
    private final CodeProducer codeProducer;

    public CodeService(CodeRepository codeRepository, CodeProducer codeProducer) {
        this.codeRepository = codeRepository;
        this.codeProducer = codeProducer;
    }

    @Transactional
    public void validateAndUseCode(CodeReceivedMessageDTO message) {

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

        codeProducer.sendCodeValidated(message.orderId());
    }
}
