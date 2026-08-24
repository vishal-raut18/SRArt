package com.srarts.product.font.repository;

import com.srarts.common.enums.OptionStatus;
import com.srarts.product.font.entity.ProductFont;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductFontRepository
        extends JpaRepository<ProductFont, Long> {

    List<ProductFont> findByProductIdAndStatus(
            Long productId,
            OptionStatus status
    );

    boolean existsByProductIdAndFontId(
            Long productId,
            Long fontId
    );
    void deleteByProductIdAndFontId(
            Long productId,
            Long fontId
    );

    List<ProductFont> findByProductId(Long productId);
}