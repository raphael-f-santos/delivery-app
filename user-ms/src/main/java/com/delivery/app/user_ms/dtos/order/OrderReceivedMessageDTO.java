package com.delivery.app.user_ms.dtos.order;

public record OrderReceivedMessageDTO(
        Long orderId,
        Long userId
) { }