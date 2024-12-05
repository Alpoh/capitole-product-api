/*-- Create the database
CREATE DATABASE products_db;

-- Switch to the new database
USE products_db;*/

--
CREATE TABLE product
(
    sku         VARCHAR(50) PRIMARY KEY,
    price       DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255)   NOT NULL,
    category    VARCHAR(100)   NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

