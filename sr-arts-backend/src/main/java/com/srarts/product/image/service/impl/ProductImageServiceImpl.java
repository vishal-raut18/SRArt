package com.srarts.product.image.service.impl;

import com.srarts.common.enums.ProductImageType;
import com.srarts.common.exception.ProductImageLimitException;
import com.srarts.common.exception.ProductImageNotFoundException;
import com.srarts.common.exception.ProductNotFoundException;
import com.srarts.product.entity.Product;
import com.srarts.product.image.dto.CreateProductImageRequest;
import com.srarts.product.image.dto.ProductImageResponse;
import com.srarts.product.image.entity.ProductImage;
import com.srarts.product.image.repository.ProductImageRepository;
import com.srarts.product.image.service.ProductImageService;
import com.srarts.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductImageServiceImpl
        implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductImageResponse addImage(
            Long productId,
            CreateProductImageRequest request) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + productId
                        )
                );

        if (request.imageType() == ProductImageType.COVER) {

            boolean coverExists =
                    productImageRepository
                            .existsByProductIdAndImageType(
                                    productId,
                                    ProductImageType.COVER
                            );

            if (coverExists) {
                throw new ProductImageLimitException(
                        "Product already has a cover image"
                );
            }
        }

        if (request.imageType() == ProductImageType.GALLERY) {

            long galleryCount =
                    productImageRepository
                            .countByProductIdAndImageType(
                                    productId,
                                    ProductImageType.GALLERY
                            );

            if (galleryCount >= 10) {
                throw new ProductImageLimitException(
                        "A product can have maximum 10 gallery images"
                );
            }
        }

        ProductImage image = ProductImage.builder()
                .product(product)
                .imageUrl(request.imageUrl())
                .imageType(request.imageType())
                .displayOrder(request.displayOrder())
                .build();

        ProductImage saved =
                productImageRepository.save(image);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductImageResponse> getProductImages(
            Long productId) {

        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(
                    "Product not found with id: " + productId
            );
        }

        return productImageRepository
                .findByProductIdOrderByDisplayOrderAsc(productId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void deleteImage(Long imageId) {

        ProductImage image =
                productImageRepository.findById(imageId)
                        .orElseThrow(() ->
                                new ProductImageNotFoundException(
                                        "Product image not found with id: "
                                                + imageId
                                )
                        );

        productImageRepository.delete(image);
    }

    private ProductImageResponse mapToResponse(
            ProductImage image) {

        return new ProductImageResponse(
                image.getId(),
                image.getProduct().getId(),
                image.getImageUrl(),
                image.getImageType(),
                image.getDisplayOrder()
        );
    }
}