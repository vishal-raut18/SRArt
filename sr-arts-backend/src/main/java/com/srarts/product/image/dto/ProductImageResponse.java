package com.srarts.product.image.dto;

import com.srarts.common.enums.ProductImageType;

public record ProductImageResponse(

        Long id,

        Long productId,

        String imageUrl,

        ProductImageType imageType,

        Integer displayOrder
) {
}