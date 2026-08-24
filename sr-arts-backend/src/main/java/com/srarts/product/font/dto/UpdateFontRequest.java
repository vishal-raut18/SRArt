package com.srarts.product.font.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFontRequest {

    @NotBlank(message = "Font name is required")
    @Size(max = 100, message = "Font name must not exceed 100 characters")
    private String name;

    @Size(max = 150, message = "Font family must not exceed 150 characters")
    private String fontFamily;

    @Size(max = 500, message = "Preview URL must not exceed 500 characters")
    private String previewUrl;
}