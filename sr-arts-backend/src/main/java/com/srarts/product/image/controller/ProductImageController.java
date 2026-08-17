package com.srarts.product.image.controller;

import com.srarts.common.response.ApiResponse;
import com.srarts.product.image.dto.CreateProductImageRequest;
import com.srarts.product.image.dto.ProductImageResponse;
import com.srarts.product.image.service.ProductImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/{productId}/images")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ProductImageResponse>> addImage(
            @PathVariable Long productId,
            @Valid @RequestBody CreateProductImageRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Product image added successfully",
                                productImageService.addImage(
                                        productId,
                                        request
                                )
                        )
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductImageResponse>>>
    getProductImages(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product images fetched successfully",
                        productImageService.getProductImages(
                                productId
                        )
                )
        );
    }

    @DeleteMapping("/{imageId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteImage(
            @PathVariable Long productId,
            @PathVariable Long imageId) {

        productImageService.deleteImage(imageId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product image deleted successfully",
                        null
                )
        );
    }
}