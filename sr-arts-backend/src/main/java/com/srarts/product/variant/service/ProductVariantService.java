package com.srarts.product.variant.service;

import com.srarts.product.variant.dto.CreateProductVariantRequest;
import com.srarts.product.variant.dto.ProductVariantResponse;

import java.util.List;

public interface ProductVariantService {

    ProductVariantResponse createVariant(
            Long productId,
            CreateProductVariantRequest request
    );

    List<ProductVariantResponse> getActiveVariants(
            Long productId
    );

    ProductVariantResponse getVariant(Long variantId);

    void deactivateVariant(Long variantId);
}