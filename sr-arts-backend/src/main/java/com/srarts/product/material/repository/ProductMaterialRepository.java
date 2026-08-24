package com.srarts.product.material.repository;

import com.srarts.common.enums.OptionStatus;
import com.srarts.product.material.entity.ProductMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductMaterialRepository
        extends JpaRepository<ProductMaterial, Long> {

    List<ProductMaterial> findByProductIdAndStatus(
            Long productId,
            OptionStatus status
    );

    boolean existsByProductIdAndMaterialId(
            Long productId,
            Long materialId
    );
    void deleteByProductIdAndMaterialId(
            Long productId,
            Long materialId
    );

    List<ProductMaterial> findByProductId(Long productId);
}