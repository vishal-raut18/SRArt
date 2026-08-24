package com.srarts.product.material.repository;

import com.srarts.common.enums.OptionStatus;
import com.srarts.product.material.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository
        extends JpaRepository<Material, Long> {

    Optional<Material> findByCode(String code);

    boolean existsByCode(String code);

    List<Material> findByStatusOrderByNameAsc(
            OptionStatus status
    );
}