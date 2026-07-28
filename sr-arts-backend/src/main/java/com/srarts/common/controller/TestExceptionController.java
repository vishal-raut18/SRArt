package com.srarts.common.controller;

import com.srarts.common.exception.ResourceNotFoundException;
import com.srarts.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestExceptionController {

    @GetMapping("/success")
    public ApiResponse<String> success() {

        return ApiResponse.success(
                "Test successful",
                "SR Arts backend is working"
        );
    }

    @GetMapping("/not-found")
    public ApiResponse<Void> notFound() {

        throw new ResourceNotFoundException(
                "Test resource was not found"
        );
    }
}