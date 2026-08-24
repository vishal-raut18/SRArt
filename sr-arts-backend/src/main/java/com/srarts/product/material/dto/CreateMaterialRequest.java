package com.srarts.product.material.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMaterialRequest {

    @NotBlank(message = "Material name is required")
    @Size(max = 100, message = "Material name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Material code is required")
    @Size(max = 50, message = "Material code must not exceed 50 characters")
    private String code;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
}