package com.srarts.product.variant.repository;

import com.srarts.common.enums.VariantStatus;
import com.srarts.product.variant.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantRepository
        extends JpaRepository<ProductVariant, Long> {

    List<ProductVariant>
    findByProductIdAndStatusOrderByPriceAsc(
            Long productId,
            VariantStatus status
    );
}