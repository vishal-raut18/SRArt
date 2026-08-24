package com.srarts.product.color.repository;

import com.srarts.common.enums.OptionStatus;
import com.srarts.product.color.entity.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductColorRepository
        extends JpaRepository<ProductColor, Long> {

    List<ProductColor> findByProductIdAndStatus(
            Long productId,
            OptionStatus status
    );

    boolean existsByProductIdAndColorId(
            Long productId,
            Long colorId
    );
    void deleteByProductIdAndColorId(
            Long productId,
            Long colorId
    );

    List<ProductColor> findByProductId(Long productId);
}