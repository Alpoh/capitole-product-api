CREATE TABLE products
(
    sku         VARCHAR(10) PRIMARY KEY,
    price       DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255)   NOT NULL,
    category    VARCHAR(100)   NOT NULL
);
