package com.srarts.product.material.service.impl;

import com.srarts.common.enums.OptionStatus;
import com.srarts.common.exception.DuplicateResourceException;
import com.srarts.common.exception.InactiveResourceException;
import com.srarts.common.exception.ResourceNotFoundException;
import com.srarts.product.material.dto.MaterialResponse;
import com.srarts.product.material.entity.Material;
import com.srarts.product.material.entity.ProductMaterial;
import com.srarts.product.material.repository.MaterialRepository;
import com.srarts.product.material.repository.ProductMaterialRepository;
import com.srarts.product.material.service.ProductMaterialService;
import com.srarts.product.entity.Product;
import com.srarts.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductMaterialServiceImpl
        implements ProductMaterialService {

    private final ProductRepository productRepository;
    private final MaterialRepository materialRepository;
    private final ProductMaterialRepository productMaterialRepository;

    @Override
    public void assignMaterial(
            Long productId,
            Long materialId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + productId
                        )
                );

        Material material = materialRepository.findById(materialId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Material not found with id: " + materialId
                        )
                );

        if (material.getStatus() != OptionStatus.ACTIVE) {
            throw new InactiveResourceException(
                    "Cannot assign inactive material"
            );
        }

        if (productMaterialRepository
                .existsByProductIdAndMaterialId(
                        productId,
                        materialId)) {

            throw new DuplicateResourceException(
                    "Material is already assigned to this product"
            );
        }

        ProductMaterial productMaterial =
                ProductMaterial.builder()
                        .product(product)
                        .material(material)
                        .status(OptionStatus.ACTIVE)
                        .build();

        productMaterialRepository.save(productMaterial);
    }

    @Override
    public void removeMaterial(
            Long productId,
            Long materialId) {

        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException(
                    "Product not found with id: " + productId
            );
        }

        if (!materialRepository.existsById(materialId)) {
            throw new ResourceNotFoundException(
                    "Material not found with id: " + materialId
            );
        }

        if (!productMaterialRepository
                .existsByProductIdAndMaterialId(
                        productId,
                        materialId)) {

            throw new ResourceNotFoundException(
                    "Material is not assigned to this product"
            );
        }

        productMaterialRepository
                .deleteByProductIdAndMaterialId(
                        productId,
                        materialId
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaterialResponse> getProductMaterials(
            Long productId) {

        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException(
                    "Product not found with id: " + productId
            );
        }

        return productMaterialRepository
                .findByProductId(productId)
                .stream()
                .filter(pm ->
                        pm.getStatus() == OptionStatus.ACTIVE
                                && pm.getMaterial().getStatus() == OptionStatus.ACTIVE
                )
                .map(pm -> mapToResponse(pm.getMaterial()))
                .toList();
    }

    private MaterialResponse mapToResponse(
            Material material) {

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