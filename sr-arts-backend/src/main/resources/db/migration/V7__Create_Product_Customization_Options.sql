-- ============================================
-- MATERIAL MASTER
-- ============================================

CREATE TABLE materials (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           name VARCHAR(100) NOT NULL,
                           code VARCHAR(50) NOT NULL,
                           description VARCHAR(500),
                           status VARCHAR(30) NOT NULL,
                           created_at DATETIME NOT NULL,
                           updated_at DATETIME,

                           PRIMARY KEY (id),

                           CONSTRAINT uk_materials_code
                               UNIQUE (code)
);


-- ============================================
-- FONT MASTER
-- ============================================

CREATE TABLE fonts (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(100) NOT NULL,
                       code VARCHAR(50) NOT NULL,
                       font_family VARCHAR(150),
                       preview_url VARCHAR(500),
                       status VARCHAR(30) NOT NULL,
                       created_at DATETIME NOT NULL,
                       updated_at DATETIME,

                       PRIMARY KEY (id),

                       CONSTRAINT uk_fonts_code
                           UNIQUE (code)
);


-- ============================================
-- COLOR MASTER
-- ============================================

CREATE TABLE colors (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        name VARCHAR(100) NOT NULL,
                        code VARCHAR(50) NOT NULL,
                        hex_code VARCHAR(7),
                        status VARCHAR(30) NOT NULL,
                        created_at DATETIME NOT NULL,
                        updated_at DATETIME,

                        PRIMARY KEY (id),

                        CONSTRAINT uk_colors_code
                            UNIQUE (code)
);


-- ============================================
-- PRODUCT ↔ MATERIAL
-- ============================================

CREATE TABLE product_materials (
                                   id BIGINT NOT NULL AUTO_INCREMENT,
                                   product_id BIGINT NOT NULL,
                                   material_id BIGINT NOT NULL,
                                   status VARCHAR(30) NOT NULL,
                                   created_at DATETIME NOT NULL,
                                   updated_at DATETIME,

                                   PRIMARY KEY (id),

                                   CONSTRAINT uk_product_material
                                       UNIQUE (product_id, material_id),

                                   CONSTRAINT fk_product_material_product
                                       FOREIGN KEY (product_id)
                                           REFERENCES products(id),

                                   CONSTRAINT fk_product_material_material
                                       FOREIGN KEY (material_id)
                                           REFERENCES materials(id)
);


-- ============================================
-- PRODUCT ↔ FONT
-- ============================================

CREATE TABLE product_fonts (
                               id BIGINT NOT NULL AUTO_INCREMENT,
                               product_id BIGINT NOT NULL,
                               font_id BIGINT NOT NULL,
                               status VARCHAR(30) NOT NULL,
                               created_at DATETIME NOT NULL,
                               updated_at DATETIME,

                               PRIMARY KEY (id),

                               CONSTRAINT uk_product_font
                                   UNIQUE (product_id, font_id),

                               CONSTRAINT fk_product_font_product
                                   FOREIGN KEY (product_id)
                                       REFERENCES products(id),

                               CONSTRAINT fk_product_font_font
                                   FOREIGN KEY (font_id)
                                       REFERENCES fonts(id)
);


-- ============================================
-- PRODUCT ↔ COLOR
-- ============================================

CREATE TABLE product_colors (
                                id BIGINT NOT NULL AUTO_INCREMENT,
                                product_id BIGINT NOT NULL,
                                color_id BIGINT NOT NULL,
                                status VARCHAR(30) NOT NULL,
                                created_at DATETIME NOT NULL,
                                updated_at DATETIME,

                                PRIMARY KEY (id),

                                CONSTRAINT uk_product_color
                                    UNIQUE (product_id, color_id),

                                CONSTRAINT fk_product_color_product
                                    FOREIGN KEY (product_id)
                                        REFERENCES products(id),

                                CONSTRAINT fk_product_color_color
                                    FOREIGN KEY (color_id)
                                        REFERENCES colors(id)
);