package com.srarts.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateProductRequest(

        @NotNull(message = "Category id is required")
        Long categoryId,

        @NotBlank(message = "Product name is required")
        @Size(max = 150, message = "Product name must not exceed 150 characters")
        String name,

        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        String description,

        @NotNull(message = "Base price is required")
        @DecimalMin(
                value = "0.0",
                inclusive = false,
                message = "Base price must be greater than zero"
        )
        BigDecimal basePrice
) {
}