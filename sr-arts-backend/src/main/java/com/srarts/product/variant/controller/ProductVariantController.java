package com.srarts.product.variant.controller;

import com.srarts.common.response.ApiResponse;
import com.srarts.product.variant.dto.CreateProductVariantRequest;
import com.srarts.product.variant.dto.ProductVariantResponse;
import com.srarts.product.variant.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;


    /**
     * Admin creates a new product variant.
     */
    @PostMapping("/products/{productId}/variants")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ProductVariantResponse>> createVariant(
            @PathVariable Long productId,
            @Valid @RequestBody CreateProductVariantRequest request) {

        ProductVariantResponse response =
                productVariantService.createVariant(
                        productId,
                        request
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Product variant created successfully.",
                                response
                        )
                );
    }


    /**
     * Public endpoint.
     * Customer can see available variants without login.
     */
    @GetMapping("/products/{productId}/variants")
    public ResponseEntity<ApiResponse<List<ProductVariantResponse>>>
    getActiveVariants(
            @PathVariable Long productId) {

        List<ProductVariantResponse> variants =
                productVariantService.getActiveVariants(
                        productId
                );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product variants fetched successfully.",
                        variants
                )
        );
    }


    /**
     * Get a particular variant.
     */
    @GetMapping("/products/variants/{variantId}")
    public ResponseEntity<ApiResponse<ProductVariantResponse>>
    getVariant(
            @PathVariable Long variantId) {

        ProductVariantResponse response =
                productVariantService.getVariant(
                        variantId
                );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product variant fetched successfully.",
                        response
                )
        );
    }


    /**
     * Admin deactivates a variant.
     */
    @PatchMapping("/products/variants/{variantId}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deactivateVariant(
            @PathVariable Long variantId) {

        productVariantService.deactivateVariant(
                variantId
        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product variant deactivated successfully.",
                        null
                )
        );
    }
}