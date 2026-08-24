package com.srarts.product.material.controller;

import com.srarts.common.response.ApiResponse;
import com.srarts.product.material.dto.*;
import com.srarts.product.material.service.MaterialService;
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
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping("/admin/materials")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<MaterialResponse>> create(
            @Valid @RequestBody CreateMaterialRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Material created successfully.",
                        materialService.create(request)
                ));
    }

    @GetMapping("/materials/{id}")
    public ResponseEntity<ApiResponse<MaterialResponse>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Material retrieved successfully.",
                        materialService.getById(id)
                )
        );
    }

    @GetMapping("/materials")
    public ResponseEntity<ApiResponse<List<MaterialResponse>>> getActive() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Materials retrieved successfully.",
                        materialService.getActiveMaterials()
                )
        );
    }

    @PutMapping("/admin/materials/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<MaterialResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMaterialRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Material updated successfully.",
                        materialService.update(id, request)
                )
        );
    }

    @PatchMapping("/admin/materials/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<MaterialResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Material status updated successfully.",
                        materialService.updateStatus(id, status)
                )
        );
    }
}