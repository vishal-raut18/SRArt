package com.srarts.product.font.repository;

import com.srarts.common.enums.OptionStatus;
import com.srarts.product.font.entity.Font;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FontRepository
        extends JpaRepository<Font, Long> {

    Optional<Font> findByCode(String code);

    boolean existsByCode(String code);

    List<Font> findByStatusOrderByNameAsc(
            OptionStatus status
    );
}