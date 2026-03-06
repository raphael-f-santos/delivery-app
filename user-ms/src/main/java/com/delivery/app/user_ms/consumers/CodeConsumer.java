package com.delivery.app.user_ms.consumers;

import com.delivery.app.user_ms.dtos.code.CodeReceivedMessageDTO;
import com.delivery.app.user_ms.services.CodeService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CodeConsumer {

    private final CodeService codeService;

    public CodeConsumer(CodeService codeService) {
        this.codeService = codeService;
    }

    @RabbitListener(queues = "${broker.queue.code.validated.name}")
    public void validateCode(CodeReceivedMessageDTO message) {
        codeService.validateAndUseCode(message);
    }
}
