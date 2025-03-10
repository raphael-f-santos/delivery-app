package com.delivery.app.eatery_ms.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(
        @NotBlank String name,
        @NotBlank String address
) { }
