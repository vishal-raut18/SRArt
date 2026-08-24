package com.srarts.product.material.entity;

import com.srarts.common.entity.BaseEntity;
import com.srarts.common.enums.OptionStatus;
import com.srarts.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "product_materials",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_product_material",
                        columnNames = {"product_id", "material_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductMaterial extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_product_material_product")
    )
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "material_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_product_material_material")
    )
    private Material material;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OptionStatus status;
}