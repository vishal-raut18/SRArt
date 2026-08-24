package com.srarts.product.color.controller;

import com.srarts.common.response.ApiResponse;
import com.srarts.product.color.dto.ColorResponse;
import com.srarts.product.color.service.ProductColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductColorController {

    private final ProductColorService productColorService;

    @PostMapping(
            "/admin/products/{productId}/colors/{colorId}"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> assignColor(
            @PathVariable Long productId,
            @PathVariable Long colorId) {

        productColorService.assignColor(productId, colorId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Color assigned to product successfully.",
                        null
                )
        );
    }

    @DeleteMapping(
            "/admin/products/{productId}/colors/{colorId}"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> removeColor(
            @PathVariable Long productId,
            @PathVariable Long colorId) {

        productColorService.removeColor(productId, colorId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Color removed from product successfully.",
                        null
                )
        );
    }

    @GetMapping(
            "/products/{productId}/colors"
    )
    public ResponseEntity<ApiResponse<List<ColorResponse>>>
    getProductColors(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product colors retrieved successfully.",
                        productColorService.getProductColors(productId)
                )
        );
    }
}