package com.srarts.product.color.service.impl;

import com.srarts.common.enums.OptionStatus;
import com.srarts.common.exception.DuplicateResourceException;
import com.srarts.common.exception.ResourceNotFoundException;
import com.srarts.product.color.dto.*;
import com.srarts.product.color.entity.Color;
import com.srarts.product.color.repository.ColorRepository;
import com.srarts.product.color.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public ColorResponse create(CreateColorRequest request) {

        if (colorRepository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException(
                    "Color with code '" + request.getCode() + "' already exists"
            );
        }

        Color color = Color.builder()
                .name(request.getName())
                .code(request.getCode())
                .hexCode(request.getHexCode())
                .status(OptionStatus.ACTIVE)
                .build();

        return mapToResponse(colorRepository.save(color));
    }

    @Override
    @Transactional(readOnly = true)
    public ColorResponse getById(Long id) {

        Color color = colorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Color not found with id: " + id
                        )
                );

        return mapToResponse(color);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ColorResponse> getActiveColors() {

        return colorRepository
                .findByStatusOrderByNameAsc(OptionStatus.ACTIVE)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ColorResponse update(
            Long id,
            UpdateColorRequest request) {

        Color color = colorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Color not found with id: " + id
                        )
                );

        color.setName(request.getName());
        color.setHexCode(request.getHexCode());

        return mapToResponse(color);
    }

    @Override
    public ColorResponse updateStatus(
            Long id,
            String status) {

        Color color = colorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Color not found with id: " + id
                        )
                );

        try {
            color.setStatus(
                    OptionStatus.valueOf(status.toUpperCase())
            );
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Invalid color status: " + status
            );
        }

        return mapToResponse(color);
    }

    private ColorResponse mapToResponse(Color color) {

        return ColorResponse.builder()
                .id(color.getId())
                .name(color.getName())
                .code(color.getCode())
                .hexCode(color.getHexCode())
                .status(color.getStatus())
                .createdAt(color.getCreatedAt())
                .updatedAt(color.getUpdatedAt())
                .build();
    }
}