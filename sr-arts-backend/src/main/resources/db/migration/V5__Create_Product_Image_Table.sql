CREATE TABLE product_images (
                                id BIGINT NOT NULL AUTO_INCREMENT,

                                product_id BIGINT NOT NULL,

                                image_url VARCHAR(1000) NOT NULL,

                                image_type VARCHAR(20) NOT NULL,

                                display_order INT NOT NULL,

                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                                    ON UPDATE CURRENT_TIMESTAMP,

                                PRIMARY KEY (id),

                                CONSTRAINT fk_product_images_product
                                    FOREIGN KEY (product_id)
                                        REFERENCES products(id),

                                CONSTRAINT chk_product_image_type
                                    CHECK (image_type IN ('COVER', 'GALLERY')),

                                CONSTRAINT chk_product_image_display_order
                                    CHECK (display_order >= 0)
);

CREATE INDEX idx_product_images_product_id
    ON product_images(product_id);