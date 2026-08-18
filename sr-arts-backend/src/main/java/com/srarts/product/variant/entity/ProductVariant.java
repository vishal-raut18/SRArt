package com.srarts.product.variant.entity;

import com.srarts.common.entity.BaseEntity;
import com.srarts.common.enums.DimensionUnit;
import com.srarts.common.enums.VariantStatus;
import com.srarts.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "product_variants",
        indexes = {
                @Index(
                        name = "idx_product_variants_product_id",
                        columnList = "product_id"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariant extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_product_variants_product"
            )
    )
    private Product product;

    @Column(
            name = "variant_name",
            nullable = false,
            length = 150
    )
    private String variantName;

    @Column(
            name = "width",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal width;

    @Column(
            name = "height",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal height;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "dimension_unit",
            nullable = false,
            length = 20
    )
    private DimensionUnit dimensionUnit;

    @Column(
            name = "price",
            nullable = false,
            precision = 12,
            scale = 2
    )
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false,
            length = 20
    )
    @Builder.Default
    private VariantStatus status = VariantStatus.ACTIVE;
}