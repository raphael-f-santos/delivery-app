package com.delivery.app.user_ms.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(
        @NotBlank String name,
        @NotBlank String address
) { }
