
package com.srarts.product.font.service.impl;

import com.srarts.common.enums.OptionStatus;
import com.srarts.common.exception.DuplicateResourceException;
import com.srarts.common.exception.InactiveResourceException;
import com.srarts.common.exception.ResourceNotFoundException;
import com.srarts.product.font.dto.FontResponse;
import com.srarts.product.font.entity.Font;
import com.srarts.product.font.entity.ProductFont;
import com.srarts.product.font.repository.FontRepository;
import com.srarts.product.font.repository.ProductFontRepository;
import com.srarts.product.font.service.ProductFontService;
import com.srarts.product.entity.Product;
import com.srarts.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductFontServiceImpl
        implements ProductFontService {

    private final ProductRepository productRepository;
    private final FontRepository fontRepository;
    private final ProductFontRepository productFontRepository;

    @Override
    public void assignFont(
            Long productId,
            Long fontId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + productId
                        )
                );

        Font font = fontRepository.findById(fontId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Font not found with id: " + fontId
                        )
                );

        if (font.getStatus() != OptionStatus.ACTIVE) {
            throw new InactiveResourceException(
                    "Cannot assign inactive font"
            );
        }

        if (productFontRepository
                .existsByProductIdAndFontId(
                        productId,
                        fontId)) {

            throw new DuplicateResourceException(
                    "Font is already assigned to this product"
            );
        }

        ProductFont productFont =
                ProductFont.builder()
                        .product(product)
                        .font(font)
                        .build();

        productFontRepository.save(productFont);
    }

    @Override
    public void removeFont(
            Long productId,
            Long fontId) {

        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException(
                    "Product not found with id: " + productId
            );
        }

        if (!fontRepository.existsById(fontId)) {
            throw new ResourceNotFoundException(
                    "Font not found with id: " + fontId
            );
        }

        if (!productFontRepository
                .existsByProductIdAndFontId(
                        productId,
                        fontId)) {

            throw new ResourceNotFoundException(
                    "Font is not assigned to this product"
            );
        }

        productFontRepository
                .deleteByProductIdAndFontId(
                        productId,
                        fontId
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<FontResponse> getProductFonts(
            Long productId) {

        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException(
                    "Product not found with id: " + productId
            );
        }

        return productFontRepository
                .findByProductId(productId)
                .stream()
                .filter(pf ->
                        pf.getStatus() == OptionStatus.ACTIVE
                                && pf.getFont().getStatus() == OptionStatus.ACTIVE
                )
                .map(pf -> mapToResponse(pf.getFont()))
                .toList();
    }

    private FontResponse mapToResponse(
            Font font) {

        return FontResponse.builder()
                .id(font.getId())
                .name(font.getName())
                .code(font.getCode())
                .status(font.getStatus())
                .createdAt(font.getCreatedAt())
                .updatedAt(font.getUpdatedAt())
                .build();
    }
}

