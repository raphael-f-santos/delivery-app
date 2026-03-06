package com.delivery.app.user_ms.dtos.order;

import com.delivery.app.user_ms.dtos.user.UserRecordDto;
import com.delivery.app.user_ms.enums.Menu;
import com.delivery.app.user_ms.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRecordDTO(
        Long id,
        @NotNull Menu menu,
        @Positive int quantity,
        @Valid UserRecordDto user
) { }

