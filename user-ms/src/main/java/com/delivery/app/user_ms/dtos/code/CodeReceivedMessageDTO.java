package com.delivery.app.user_ms.dtos.code;

public record CodeReceivedMessageDTO(
        Long userId,
        Long orderId,
        int code
) { }
