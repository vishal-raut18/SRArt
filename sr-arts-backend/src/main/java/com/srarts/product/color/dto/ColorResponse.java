package com.srarts.product.color.dto;

import com.srarts.common.enums.OptionStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ColorResponse {

    private Long id;

    private String name;

    private String code;

    private String hexCode;

    private OptionStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}