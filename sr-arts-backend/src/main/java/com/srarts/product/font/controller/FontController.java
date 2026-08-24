package com.srarts.product.font.controller;

import com.srarts.common.response.ApiResponse;
import com.srarts.product.font.dto.*;
import com.srarts.product.font.service.FontService;
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
public class FontController {

    private final FontService fontService;

    @PostMapping("/admin/fonts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<FontResponse>> create(
            @Valid @RequestBody CreateFontRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Font created successfully.",
                        fontService.create(request)
                ));
    }

    @GetMapping("/fonts/{id}")
    public ResponseEntity<ApiResponse<FontResponse>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Font retrieved successfully.",
                        fontService.getById(id)
                )
        );
    }

    @GetMapping("/fonts")
    public ResponseEntity<ApiResponse<List<FontResponse>>> getActive() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Fonts retrieved successfully.",
                        fontService.getActiveFonts()
                )
        );
    }

    @PutMapping("/admin/fonts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<FontResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFontRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Font updated successfully.",
                        fontService.update(id, request)
                )
        );
    }

    @PatchMapping("/admin/fonts/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<FontResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Font status updated successfully.",
                        fontService.updateStatus(id, status)
                )
        );
    }
}