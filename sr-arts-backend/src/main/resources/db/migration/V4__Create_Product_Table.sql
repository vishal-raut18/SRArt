CREATE TABLE products (
                          id BIGINT NOT NULL AUTO_INCREMENT,

                          category_id BIGINT NOT NULL,

                          name VARCHAR(150) NOT NULL,
                          description VARCHAR(1000),
                          base_price DECIMAL(12,2) NOT NULL,

                          status VARCHAR(20) NOT NULL DEFAULT 'DRAFT',

                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                              ON UPDATE CURRENT_TIMESTAMP,

                          PRIMARY KEY (id),

                          CONSTRAINT uk_products_name
                              UNIQUE (name),

                          CONSTRAINT fk_products_category
                              FOREIGN KEY (category_id)
                                  REFERENCES categories(id)
);