package com.srarts.product.dto;

import com.srarts.common.enums.ProductStatus;

import java.math.BigDecimal;

public record ProductResponse(

        Long id,

        Long categoryId,

        String categoryName,

        String name,

        String description,

        BigDecimal basePrice,

        ProductStatus status
) {
}