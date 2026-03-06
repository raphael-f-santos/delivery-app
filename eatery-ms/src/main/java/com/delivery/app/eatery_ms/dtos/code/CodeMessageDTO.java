package com.delivery.app.eatery_ms.dtos.code;

public record CodeMessageDTO(
        Long userId,
        Long orderId,
        int code
) { }
