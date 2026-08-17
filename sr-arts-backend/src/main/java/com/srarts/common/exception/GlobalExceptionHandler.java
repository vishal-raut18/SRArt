package com.srarts.common.exception;

import com.srarts.common.constants.ErrorCode;
import com.srarts.common.response.ApiError;
import com.srarts.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(
            ResourceNotFoundException exception) {

        ApiError error = ApiError.builder()
                .code(ErrorCode.RESOURCE_NOT_FOUND)
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.failure(
                        exception.getMessage(),
                        error
                ));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateResource(
            DuplicateResourceException exception) {

        ApiError error = ApiError.builder()
                .code(ErrorCode.DUPLICATE_RESOURCE)
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.failure(
                        exception.getMessage(),
                        error
                ));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(
            BusinessException exception) {

        ApiError error = ApiError.builder()
                .code(ErrorCode.BUSINESS_ERROR)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.failure(
                        exception.getMessage(),
                        error
                ));
    }

    @ExceptionHandler(
            org.springframework.web.bind.MethodArgumentNotValidException.class
    )
    public ResponseEntity<ApiResponse<Void>> handleValidationException(
            org.springframework.web.bind.MethodArgumentNotValidException exception) {

        var errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(java.util.stream.Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage(),
                        (existing, replacement) -> existing
                ));

        ApiError error = ApiError.builder()
                .code(ErrorCode.VALIDATION_ERROR)
                .details(errors)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.failure(
                        "Validation failed",
                        error
                ));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpectedException(
            Exception exception) {

        ApiError error = ApiError.builder()
                .code(ErrorCode.INTERNAL_SERVER_ERROR)
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.failure(
                        "An unexpected error occurred",
                        error
                ));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadCredentials(
            BadCredentialsException exception) {

        ApiError error = ApiError.builder()
                .code(ErrorCode.UNAUTHORIZED)
                .build();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.failure(
                        "Invalid username or password",
                        error
                ));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAuthorizationDenied(
            AuthorizationDeniedException exception) {

        ApiError error = ApiError.builder()
                .code("FORBIDDEN")
                .details(Map.of(
                        "reason", "You do not have permission to perform this action."
                ))
                .build();

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.failure(
                        "Access denied.",
                        error
                ));
    }
    @ExceptionHandler(DuplicateCategoryException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateCategory(
            DuplicateCategoryException ex) {

        ApiError error = ApiError.builder()
                .code("DUPLICATE_CATEGORY")
                .details(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.failure(
                        ex.getMessage(),
                        error
                ));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCategoryNotFound(
            CategoryNotFoundException ex) {

        ApiError error = ApiError.builder()
                .code("CATEGORY_NOT_FOUND")
                .details(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.failure(
                        ex.getMessage(),
                        error
                ));
    }
    @ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateProduct(
            DuplicateProductException ex) {

        ApiError error = ApiError.builder()
                .code("DUPLICATE_PRODUCT")
                .details(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.failure(
                        ex.getMessage(),
                        error
                ));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleProductNotFound(
            ProductNotFoundException ex) {

        ApiError error = ApiError.builder()
                .code("PRODUCT_NOT_FOUND")
                .details(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.failure(
                        ex.getMessage(),
                        error
                ));
    }
    @ExceptionHandler(ProductImageNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleProductImageNotFound(
            ProductImageNotFoundException exception) {

        ApiError error = ApiError.builder()
                .code("PRODUCT_IMAGE_NOT_FOUND")
                .details(Map.of(
                        "reason", exception.getMessage()
                ))
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ApiResponse.failure(
                                "Product image not found.",
                                error
                        )
                );
    }
    @ExceptionHandler(ProductImageLimitException.class)
    public ResponseEntity<ApiResponse<Void>> handleProductImageLimit(
            ProductImageLimitException exception) {

        ApiError error = ApiError.builder()
                .code("PRODUCT_IMAGE_LIMIT")
                .details(Map.of(
                        "reason", exception.getMessage()
                ))
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiResponse.failure(
                                "Product image limit exceeded.",
                                error
                        )
                );
    }
}