package com.srarts.product.font.controller;

import com.srarts.common.response.ApiResponse;
import com.srarts.product.font.dto.FontResponse;
import com.srarts.product.font.service.ProductFontService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductFontController {

    private final ProductFontService productFontService;

    @PostMapping(
            "/admin/products/{productId}/fonts/{fontId}"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> assignFont(
            @PathVariable Long productId,
            @PathVariable Long fontId) {

        productFontService.assignFont(productId, fontId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Font assigned to product successfully.",
                        null
                )
        );
    }

    @DeleteMapping(
            "/admin/products/{productId}/fonts/{fontId}"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> removeFont(
            @PathVariable Long productId,
            @PathVariable Long fontId) {

        productFontService.removeFont(productId, fontId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Font removed from product successfully.",
                        null
                )
        );
    }

    @GetMapping(
            "/products/{productId}/fonts"
    )
    public ResponseEntity<ApiResponse<List<FontResponse>>>
    getProductFonts(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product fonts retrieved successfully.",
                        productFontService.getProductFonts(productId)
                )
        );
    }
}