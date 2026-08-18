package com.srarts.product.variant.dto;

import com.srarts.common.enums.DimensionUnit;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductVariantRequest(

        @NotBlank(message = "Variant name is required")
        @Size(max = 150)
        String variantName,

        @NotNull(message = "Width is required")
        @DecimalMin(
                value = "0.01",
                message = "Width must be greater than zero"
        )
        BigDecimal width,

        @NotNull(message = "Height is required")
        @DecimalMin(
                value = "0.01",
                message = "Height must be greater than zero"
        )
        BigDecimal height,

        @NotNull(message = "Dimension unit is required")
        DimensionUnit dimensionUnit,

        @NotNull(message = "Price is required")
        @DecimalMin(
                value = "0.01",
                message = "Price must be greater than zero"
        )
        BigDecimal price
) {
}