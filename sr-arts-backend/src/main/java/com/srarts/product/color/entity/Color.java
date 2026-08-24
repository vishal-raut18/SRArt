package com.srarts.product.color.entity;

import com.srarts.common.entity.BaseEntity;
import com.srarts.common.enums.OptionStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "colors",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_colors_code",
                        columnNames = "code"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Color extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String code;

    @Column(name = "hex_code", length = 7)
    private String hexCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OptionStatus status;
}