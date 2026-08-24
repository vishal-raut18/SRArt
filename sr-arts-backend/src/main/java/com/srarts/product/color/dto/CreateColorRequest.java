package com.srarts.product.color.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateColorRequest {

    @NotBlank(message = "Color name is required")
    @Size(max = 100, message = "Color name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Color code is required")
    @Size(max = 50, message = "Color code must not exceed 50 characters")
    private String code;

    @Pattern(
            regexp = "^#[A-Fa-f0-9]{6}$",
            message = "Hex code must be in format #RRGGBB"
    )
    private String hexCode;
}