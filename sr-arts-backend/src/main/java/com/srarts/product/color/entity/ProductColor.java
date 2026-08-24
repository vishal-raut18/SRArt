package com.srarts.product.color.entity;

import com.srarts.common.entity.BaseEntity;
import com.srarts.common.enums.OptionStatus;
import com.srarts.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "product_colors",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_product_color",
                        columnNames = {"product_id", "color_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductColor extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_product_color_product")
    )
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "color_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_product_color_color")
    )
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OptionStatus status;
}