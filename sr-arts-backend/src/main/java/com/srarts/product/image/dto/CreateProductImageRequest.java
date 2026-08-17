package com.srarts.product.image.dto;

import com.srarts.common.enums.ProductImageType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductImageRequest(

        @NotBlank(message = "Image URL is required")
        String imageUrl,

        @NotNull(message = "Image type is required")
        ProductImageType imageType,

        @NotNull(message = "Display order is required")
        @Min(value = 0, message = "Display order cannot be negative")
        Integer displayOrder
) {
}