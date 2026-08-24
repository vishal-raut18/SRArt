package com.srarts.product.color.controller;

import com.srarts.common.response.ApiResponse;
import com.srarts.product.color.dto.*;
import com.srarts.product.color.service.ColorService;
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
public class ColorController {

    private final ColorService colorService;

    @PostMapping("/admin/colors")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ColorResponse>> create(
            @Valid @RequestBody CreateColorRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Color created successfully.",
                        colorService.create(request)
                ));
    }

    @GetMapping("/colors/{id}")
    public ResponseEntity<ApiResponse<ColorResponse>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Color retrieved successfully.",
                        colorService.getById(id)
                )
        );
    }

    @GetMapping("/colors")
    public ResponseEntity<ApiResponse<List<ColorResponse>>> getActive() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Colors retrieved successfully.",
                        colorService.getActiveColors()
                )
        );
    }

    @PutMapping("/admin/colors/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ColorResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateColorRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Color updated successfully.",
                        colorService.update(id, request)
                )
        );
    }

    @PatchMapping("/admin/colors/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ColorResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Color status updated successfully.",
                        colorService.updateStatus(id, status)
                )
        );
    }
}