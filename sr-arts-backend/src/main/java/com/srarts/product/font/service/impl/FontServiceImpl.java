package com.srarts.product.font.service.impl;

import com.srarts.common.enums.OptionStatus;
import com.srarts.common.exception.DuplicateResourceException;
import com.srarts.common.exception.ResourceNotFoundException;
import com.srarts.product.font.dto.*;
import com.srarts.product.font.entity.Font;
import com.srarts.product.font.repository.FontRepository;
import com.srarts.product.font.service.FontService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FontServiceImpl implements FontService {

    private final FontRepository fontRepository;

    @Override
    public FontResponse create(CreateFontRequest request) {

        if (fontRepository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException(
                    "Font with code '" + request.getCode() + "' already exists"
            );
        }

        Font font = Font.builder()
                .name(request.getName())
                .code(request.getCode())
                .fontFamily(request.getFontFamily())
                .previewUrl(request.getPreviewUrl())
                .status(OptionStatus.ACTIVE)
                .build();

        return mapToResponse(fontRepository.save(font));
    }

    @Override
    @Transactional(readOnly = true)
    public FontResponse getById(Long id) {

        Font font = fontRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Font not found with id: " + id
                        )
                );

        return mapToResponse(font);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FontResponse> getActiveFonts() {

        return fontRepository
                .findByStatusOrderByNameAsc(OptionStatus.ACTIVE)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public FontResponse update(
            Long id,
            UpdateFontRequest request) {

        Font font = fontRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Font not found with id: " + id
                        )
                );

        font.setName(request.getName());
        font.setFontFamily(request.getFontFamily());
        font.setPreviewUrl(request.getPreviewUrl());

        return mapToResponse(font);
    }

    @Override
    public FontResponse updateStatus(
            Long id,
            String status) {

        Font font = fontRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Font not found with id: " + id
                        )
                );

        try {
            font.setStatus(
                    OptionStatus.valueOf(status.toUpperCase())
            );
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Invalid font status: " + status
            );
        }

        return mapToResponse(font);
    }

    private FontResponse mapToResponse(Font font) {

        return FontResponse.builder()
                .id(font.getId())
                .name(font.getName())
                .code(font.getCode())
                .fontFamily(font.getFontFamily())
                .previewUrl(font.getPreviewUrl())
                .status(font.getStatus())
                .createdAt(font.getCreatedAt())
                .updatedAt(font.getUpdatedAt())
                .build();
    }
}