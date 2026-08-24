package com.srarts.product.material.controller;

import com.srarts.common.response.ApiResponse;
import com.srarts.product.material.dto.MaterialResponse;
import com.srarts.product.material.service.ProductMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductMaterialController {

    private final ProductMaterialService productMaterialService;

    @PostMapping(
            "/admin/products/{productId}/materials/{materialId}"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> assignMaterial(
            @PathVariable Long productId,
            @PathVariable Long materialId) {

        productMaterialService.assignMaterial(
                productId,
                materialId
        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Material assigned to product successfully.",
                        null
                )
        );
    }

    @DeleteMapping(
            "/admin/products/{productId}/materials/{materialId}"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> removeMaterial(
            @PathVariable Long productId,
            @PathVariable Long materialId) {

        productMaterialService.removeMaterial(
                productId,
                materialId
        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Material removed from product successfully.",
                        null
                )
        );
    }

    @GetMapping(
            "/products/{productId}/materials"
    )
    public ResponseEntity<ApiResponse<List<MaterialResponse>>>
    getProductMaterials(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product materials retrieved successfully.",
                        productMaterialService
                                .getProductMaterials(productId)
                )
        );
    }
}