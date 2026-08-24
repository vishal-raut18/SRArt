package com.srarts.product.color.repository;

import com.srarts.common.enums.OptionStatus;
import com.srarts.product.color.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColorRepository
        extends JpaRepository<Color, Long> {

    Optional<Color> findByCode(String code);

    boolean existsByCode(String code);

    List<Color> findByStatusOrderByNameAsc(
            OptionStatus status
    );
}