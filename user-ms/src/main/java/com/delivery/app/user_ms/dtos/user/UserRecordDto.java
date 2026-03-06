package com.delivery.app.user_ms.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(
        Long id,
        @NotBlank String name,
        @NotBlank String address
) { }
