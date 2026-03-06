package com.delivery.app.eatery_ms.dtos.order;

import com.delivery.app.eatery_ms.enums.Menu;
import com.delivery.app.eatery_ms.enums.OrderStatus;

public record OrderRecordDTO(
        Menu menu,
        int quantity,
        OrderStatus status,
        Long userId
) { }
