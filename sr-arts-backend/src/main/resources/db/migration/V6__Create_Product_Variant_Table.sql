CREATE TABLE product_variants (
                                  id BIGINT NOT NULL AUTO_INCREMENT,

                                  product_id BIGINT NOT NULL,

                                  variant_name VARCHAR(150) NOT NULL,

                                  width DECIMAL(10,2) NOT NULL,

                                  height DECIMAL(10,2) NOT NULL,

                                  dimension_unit VARCHAR(20) NOT NULL,

                                  price DECIMAL(12,2) NOT NULL,

                                  status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

                                  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                                      ON UPDATE CURRENT_TIMESTAMP,

                                  PRIMARY KEY (id),

                                  CONSTRAINT fk_product_variants_product
                                      FOREIGN KEY (product_id)
                                          REFERENCES products(id),

                                  CONSTRAINT chk_product_variant_width
                                      CHECK (width > 0),

                                  CONSTRAINT chk_product_variant_height
                                      CHECK (height > 0),

                                  CONSTRAINT chk_product_variant_price
                                      CHECK (price > 0),

                                  CONSTRAINT chk_product_variant_unit
                                      CHECK (
                                          dimension_unit IN (
                                                             'INCH',
                                                             'FEET',
                                                             'MM',
                                                             'CM'
                                              )
                                          ),

                                  CONSTRAINT chk_product_variant_status
                                      CHECK (
                                          status IN (
                                                     'ACTIVE',
                                                     'INACTIVE'
                                              )
                                          )
);

CREATE INDEX idx_product_variants_product_id
    ON product_variants(product_id);