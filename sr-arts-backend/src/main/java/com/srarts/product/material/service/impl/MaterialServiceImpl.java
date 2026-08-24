package com.srarts.product.material.service.impl;

import com.srarts.common.enums.OptionStatus;
import com.srarts.common.exception.DuplicateResourceException;
import com.srarts.common.exception.ResourceNotFoundException;
import com.srarts.product.material.dto.CreateMaterialRequest;
import com.srarts.product.material.dto.MaterialResponse;
import com.srarts.product.material.dto.UpdateMaterialRequest;
import com.srarts.product.material.entity.Material;
import com.srarts.product.material.repository.MaterialRepository;
import com.srarts.product.material.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Override
    public MaterialResponse create(CreateMaterialRequest request) {

        if (materialRepository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException(
                    "Material with code '" + request.getCode() + "' already exists"
            );
        }

        Material material = Material.builder()
                .name(request.getName())
                .code(request.getCode())
                .description(request.getDescription())
                .status(OptionStatus.ACTIVE)
                .build();

        return mapToResponse(materialRepository.save(material));
    }

    @Override
    @Transactional(readOnly = true)
    public MaterialResponse getById(Long id) {

        Material material = materialRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Material not found with id: " + id
                        )
                );

        return mapToResponse(material);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaterialResponse> getActiveMaterials() {

        return materialRepository
                .findByStatusOrderByNameAsc(OptionStatus.ACTIVE)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public MaterialResponse update(
            Long id,
            UpdateMaterialRequest request) {

        Material material = materialRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Material not found with id: " + id
                        )
                );

        material.setName(request.getName());
        material.setDescription(request.getDescription());

        return mapToResponse(material);
    }

    @Override
    public MaterialResponse updateStatus(
            Long id,
            String status) {

        Material material = materialRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Material not found with id: " + id
                        )
                );

        OptionStatus optionStatus;

        try {
            optionStatus = OptionStatus.valueOf(
                    status.toUpperCase()
            );
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Invalid material status: " + status
            );
        }

        material.setStatus(optionStatus);

        return mapToResponse(material);
    }

    private MaterialResponse mapToResponse(Material material) {

        return MaterialResponse.builder()
                .id(material.getId())
                .name(material.getName())
                .code(material.getCode())
                .description(material.getDescription())
                .status(material.getStatus())
                .createdAt(material.getCreatedAt())
                .updatedAt(material.getUpdatedAt())
                .build();
    }
}