CREATE TABLE application_info
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_name VARCHAR(100) NOT NULL,
    version VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO application_info
(application_name, version)
VALUES
    ('SR Arts Backend', '1.0.0');