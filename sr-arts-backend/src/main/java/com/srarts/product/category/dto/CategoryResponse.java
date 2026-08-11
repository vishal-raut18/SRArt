package com.srarts.product.category.dto;

public record CategoryResponse(
        Long id,
        String name,
        String description,
        Boolean active
) {
}