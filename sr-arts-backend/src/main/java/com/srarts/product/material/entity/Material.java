package com.srarts.product.material.entity;

import com.srarts.common.entity.BaseEntity;
import com.srarts.common.enums.OptionStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "materials",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_materials_code",
                        columnNames = "code"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Material extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String code;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OptionStatus status;
}