-- Sovereign DB Schema — H2 compatible
-- Tables created in dependency order: cars first, then car_colors and mods.
-- Note: 'year' is a reserved keyword in H2, so we use 'manufacture_year'.

CREATE TABLE IF NOT EXISTS cars (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(255)  NOT NULL,
    model            VARCHAR(255)  NOT NULL,
    manufacture_year INT,
    price            DOUBLE,
    description      VARCHAR(1000),
    image_url        VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS car_colors (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    hex_code VARCHAR(20)  NOT NULL,
    car_id   BIGINT       NOT NULL,
    CONSTRAINT fk_color_car FOREIGN KEY (car_id) REFERENCES cars(id)
);

CREATE TABLE IF NOT EXISTS mods (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    price       DOUBLE,
    category    VARCHAR(100),
    car_id      BIGINT NOT NULL,
    CONSTRAINT fk_mod_car FOREIGN KEY (car_id) REFERENCES cars(id)
);

CREATE TABLE IF NOT EXISTS users (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(255) NOT NULL UNIQUE,
    email         VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role          VARCHAR(50)
);
