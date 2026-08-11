package com.srarts.product.repository;

import com.srarts.common.enums.ProductStatus;
import com.srarts.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByNameIgnoreCase(String name);

    List<Product> findByStatus(ProductStatus status);

    List<Product> findByCategoryIdAndStatus(
            Long categoryId,
            ProductStatus status
    );
}