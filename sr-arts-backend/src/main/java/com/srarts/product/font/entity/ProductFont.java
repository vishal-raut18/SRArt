package com.srarts.product.font.entity;

import com.srarts.common.entity.BaseEntity;
import com.srarts.common.enums.OptionStatus;
import com.srarts.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "product_fonts",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_product_font",
                        columnNames = {"product_id", "font_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductFont extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_product_font_product")
    )
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "font_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_product_font_font")
    )
    private Font font;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OptionStatus status;
}