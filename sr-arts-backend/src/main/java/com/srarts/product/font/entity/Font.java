package com.srarts.product.font.entity;

import com.srarts.common.entity.BaseEntity;
import com.srarts.common.enums.OptionStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "fonts",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_fonts_code",
                        columnNames = "code"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Font extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String code;

    @Column(name = "font_family", length = 150)
    private String fontFamily;

    @Column(name = "preview_url", length = 500)
    private String previewUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OptionStatus status;
}