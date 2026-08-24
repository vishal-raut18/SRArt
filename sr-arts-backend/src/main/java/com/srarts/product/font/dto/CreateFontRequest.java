package com.srarts.product.font.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFontRequest {

    @NotBlank(message = "Font name is required")
    @Size(max = 100, message = "Font name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Font code is required")
    @Size(max = 50, message = "Font code must not exceed 50 characters")
    private String code;

    @Size(max = 150, message = "Font family must not exceed 150 characters")
    private String fontFamily;

    @Size(max = 500, message = "Preview URL must not exceed 500 characters")
    private String previewUrl;
}