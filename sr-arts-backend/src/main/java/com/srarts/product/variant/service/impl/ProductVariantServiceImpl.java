package com.srarts.product.variant.service.impl;

import com.srarts.common.enums.VariantStatus;
import com.srarts.common.exception.ProductNotFoundException;
import com.srarts.common.exception.ProductVariantNotFoundException;
import com.srarts.product.entity.Product;
import com.srarts.product.repository.ProductRepository;
import com.srarts.product.variant.dto.CreateProductVariantRequest;
import com.srarts.product.variant.dto.ProductVariantResponse;
import com.srarts.product.variant.entity.ProductVariant;
import com.srarts.product.variant.repository.ProductVariantRepository;
import com.srarts.product.variant.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductVariantResponse createVariant(
            Long productId,
            CreateProductVariantRequest request) {

        // 1. Verify that product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + productId
                        )
                );

        // 2. Create ProductVariant
        ProductVariant variant = ProductVariant.builder()
                .product(product)
                .variantName(request.variantName())
                .width(request.width())
                .height(request.height())
                .dimensionUnit(request.dimensionUnit())
                .price(request.price())
                .status(VariantStatus.ACTIVE)
                .build();

        // 3. Save variant
        ProductVariant savedVariant =
                productVariantRepository.save(variant);

        // 4. Convert entity -> response
        return mapToResponse(savedVariant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductVariantResponse> getActiveVariants(
            Long productId) {

        // 1. Verify product exists
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(
                    "Product not found with id: " + productId
            );
        }

        // 2. Get only ACTIVE variants
        return productVariantRepository
                .findByProductIdAndStatusOrderByPriceAsc(
                        productId,
                        VariantStatus.ACTIVE
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductVariantResponse getVariant(Long variantId) {

        ProductVariant variant =
                productVariantRepository.findById(variantId)
                        .orElseThrow(() ->
                                new ProductVariantNotFoundException(
                                        "Product variant not found with id: "
                                                + variantId
                                )
                        );

        return mapToResponse(variant);
    }

    @Override
    public void deactivateVariant(Long variantId) {

        ProductVariant variant =
                productVariantRepository.findById(variantId)
                        .orElseThrow(() ->
                                new ProductVariantNotFoundException(
                                        "Product variant not found with id: "
                                                + variantId
                                )
                        );

        variant.setStatus(VariantStatus.INACTIVE);

        productVariantRepository.save(variant);
    }

    private ProductVariantResponse mapToResponse(
            ProductVariant variant) {

        return new ProductVariantResponse(
                variant.getId(),
                variant.getProduct().getId(),
                variant.getVariantName(),
                variant.getWidth(),
                variant.getHeight(),
                variant.getDimensionUnit(),
                variant.getPrice(),
                variant.getStatus()
        );
    }
}