CREATE TABLE categories (
                            id BIGINT NOT NULL AUTO_INCREMENT,

                            name VARCHAR(100) NOT NULL,
                            description VARCHAR(500),
                            active BOOLEAN NOT NULL DEFAULT TRUE,

                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                                ON UPDATE CURRENT_TIMESTAMP,

                            PRIMARY KEY (id),

                            CONSTRAINT uk_categories_name UNIQUE (name)
);