package com.delivery.app.user_ms.dtos.order;

import com.delivery.app.user_ms.enums.Menu;
import com.delivery.app.user_ms.enums.OrderStatus;

public record OrderMessageDTO(
        Menu menu,
        int quantity,
        OrderStatus status,
        Long userId
) {}