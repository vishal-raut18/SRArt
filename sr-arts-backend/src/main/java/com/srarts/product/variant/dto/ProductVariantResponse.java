package com.srarts.product.variant.dto;

import com.srarts.common.enums.DimensionUnit;
import com.srarts.common.enums.VariantStatus;

import java.math.BigDecimal;

public record ProductVariantResponse(

        Long id,

        Long productId,

        String variantName,

        BigDecimal width,

        BigDecimal height,

        DimensionUnit dimensionUnit,

        BigDecimal price,

        VariantStatus status
) {
}