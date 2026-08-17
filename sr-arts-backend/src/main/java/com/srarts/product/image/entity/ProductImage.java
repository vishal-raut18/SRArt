package com.srarts.product.image.entity;

import com.srarts.common.entity.BaseEntity;
import com.srarts.common.enums.ProductImageType;
import com.srarts.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "product_images",
        indexes = {
                @Index(
                        name = "idx_product_images_product_id",
                        columnList = "product_id"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_product_images_product"
            )
    )
    private Product product;

    @Column(
            name = "image_url",
            nullable = false,
            length = 1000
    )
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "image_type",
            nullable = false,
            length = 20
    )
    private ProductImageType imageType;

    @Column(
            name = "display_order",
            nullable = false
    )
    private Integer displayOrder;
}