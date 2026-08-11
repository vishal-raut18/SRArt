package com.srarts.product.controller;

import com.srarts.common.enums.ProductStatus;
import com.srarts.common.response.ApiResponse;
import com.srarts.product.dto.CreateProductRequest;
import com.srarts.product.dto.ProductResponse;
import com.srarts.product.dto.UpdateProductRequest;
import com.srarts.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @Valid @RequestBody CreateProductRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Product created successfully",
                        productService.createProduct(request)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>>
    getActiveProducts() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Products fetched successfully",
                        productService.getActiveProducts()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>>
    getProductById(@PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product fetched successfully",
                        productService.getProductById(id)
                )
        );
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<ProductResponse>>>
    getProductsByCategory(@PathVariable Long categoryId) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Products fetched successfully",
                        productService.getProductsByCategory(categoryId)
                )
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ProductResponse>>
    updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product updated successfully",
                        productService.updateProduct(id, request)
                )
        );
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ProductResponse>>
    updateProductStatus(
            @PathVariable Long id,
            @RequestParam ProductStatus status) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product status updated successfully",
                        productService.updateProductStatus(id, status)
                )
        );
    }
}