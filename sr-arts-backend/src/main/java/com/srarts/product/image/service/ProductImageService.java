package com.srarts.product.image.service;

import com.srarts.product.image.dto.CreateProductImageRequest;
import com.srarts.product.image.dto.ProductImageResponse;

import java.util.List;

public interface ProductImageService {

    ProductImageResponse addImage(
            Long productId,
            CreateProductImageRequest request
    );

    List<ProductImageResponse> getProductImages(
            Long productId
    );

    void deleteImage(Long imageId);
}