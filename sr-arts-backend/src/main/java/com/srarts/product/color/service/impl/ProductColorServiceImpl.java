package com.srarts.product.color.service.impl;

import com.srarts.common.enums.OptionStatus;
import com.srarts.common.exception.DuplicateResourceException;
import com.srarts.common.exception.InactiveResourceException;
import com.srarts.common.exception.ResourceNotFoundException;
import com.srarts.product.color.dto.ColorResponse;
import com.srarts.product.color.entity.Color;
import com.srarts.product.color.entity.ProductColor;
import com.srarts.product.color.repository.ColorRepository;
import com.srarts.product.color.repository.ProductColorRepository;
import com.srarts.product.color.service.ProductColorService;
import com.srarts.product.entity.Product;
import com.srarts.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductColorServiceImpl
        implements ProductColorService {

    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;
    private final ProductColorRepository productColorRepository;

    @Override
    public void assignColor(
            Long productId,
            Long colorId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + productId
                        )
                );

        Color color = colorRepository.findById(colorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Color not found with id: " + colorId
                        )
                );

        if (color.getStatus() != OptionStatus.ACTIVE) {
            throw new InactiveResourceException(
                    "Cannot assign inactive color"
            );
        }

        if (productColorRepository
                .existsByProductIdAndColorId(
                        productId,
                        colorId)) {

            throw new DuplicateResourceException(
                    "Color is already assigned to this product"
            );
        }

        ProductColor productColor =
                ProductColor.builder()
                        .product(product)
                        .color(color)
                        .build();

        productColorRepository.save(productColor);
    }

    @Override
    public void removeColor(
            Long productId,
            Long colorId) {

        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException(
                    "Product not found with id: " + productId
            );
        }

        if (!colorRepository.existsById(colorId)) {
            throw new ResourceNotFoundException(
                    "Color not found with id: " + colorId
            );
        }

        if (!productColorRepository
                .existsByProductIdAndColorId(
                        productId,
                        colorId)) {

            throw new ResourceNotFoundException(
                    "Color is not assigned to this product"
            );
        }

        productColorRepository
                .deleteByProductIdAndColorId(
                        productId,
                        colorId
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ColorResponse> getProductColors(
            Long productId) {

        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException(
                    "Product not found with id: " + productId
            );
        }

        return productColorRepository
                .findByProductId(productId)
                .stream()
                .filter(pc ->
                        pc.getStatus() == OptionStatus.ACTIVE
                                && pc.getColor().getStatus() == OptionStatus.ACTIVE
                )
                .map(pc -> mapToResponse(pc.getColor()))
                .toList();
    }

    private ColorResponse mapToResponse(
            Color color) {

        return ColorResponse.builder()
                .id(color.getId())
                .name(color.getName())
                .code(color.getCode())
                .status(color.getStatus())
                .createdAt(color.getCreatedAt())
                .updatedAt(color.getUpdatedAt())
                .build();
    }
}