package com.srarts.product.image.repository;

import com.srarts.common.enums.ProductImageType;
import com.srarts.product.image.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository
        extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductIdOrderByDisplayOrderAsc(
            Long productId
    );

    boolean existsByProductIdAndImageType(
            Long productId,
            ProductImageType imageType
    );

    long countByProductIdAndImageType(
            Long productId,
            ProductImageType imageType
    );
}