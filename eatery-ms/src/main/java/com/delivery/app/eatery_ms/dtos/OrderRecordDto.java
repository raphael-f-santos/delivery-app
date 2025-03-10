package com.delivery.app.eatery_ms.dtos;

import com.delivery.app.eatery_ms.enums.Menu;
import com.delivery.app.eatery_ms.enums.OrderStatus;
import com.delivery.app.eatery_ms.models.Order;
import com.delivery.app.eatery_ms.models.User;

public record OrderRecordDto(
        Menu menu,
        int quantity,
        OrderStatus status,
        UserRecordDto user
) { }
