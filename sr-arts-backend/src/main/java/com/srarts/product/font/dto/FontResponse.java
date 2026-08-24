package com.srarts.product.font.dto;

import com.srarts.common.enums.OptionStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FontResponse {

    private Long id;

    private String name;

    private String code;

    private String fontFamily;

    private String previewUrl;

    private OptionStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}