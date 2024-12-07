-- Create the PRODUCT table
CREATE TABLE PRODUCT
(
    sku         VARCHAR(50) PRIMARY KEY,
    price       DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255)   NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create the CATEGORY table
CREATE TABLE CATEGORY
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);

-- Create the DISCOUNT table
CREATE TABLE DISCOUNT
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100)  NOT NULL,
    description VARCHAR(255),
    percentage  DECIMAL(5, 2) NOT NULL
);

-- Create the PRODUCT_CATEGORY table (many-to-many relationship)
CREATE TABLE PRODUCT_CATEGORY
(
    product_sku VARCHAR(50),
    category_id BIGINT,
    PRIMARY KEY (product_sku, category_id),
    FOREIGN KEY (product_sku) REFERENCES PRODUCT (sku),
    FOREIGN KEY (category_id) REFERENCES CATEGORY (id)
);

CREATE INDEX idx_product_sku ON PRODUCT_CATEGORY (product_sku);
CREATE INDEX idx_category_id ON PRODUCT_CATEGORY (category_id);



