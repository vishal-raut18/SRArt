package com.srarts.product.service;

import com.srarts.common.enums.ProductStatus;
import com.srarts.product.dto.CreateProductRequest;
import com.srarts.product.dto.ProductResponse;
import com.srarts.product.dto.UpdateProductRequest;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest request);

    List<ProductResponse> getActiveProducts();

    ProductResponse getProductById(Long id);

    List<ProductResponse> getProductsByCategory(Long categoryId);

    ProductResponse updateProduct(
            Long id,
            UpdateProductRequest request
    );

    ProductResponse updateProductStatus(
            Long id,
            ProductStatus status
    );
}