SELECT * FROM market_data.products;

CREATE TABLE category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL UNIQUE
);
ALTER TABLE products
ADD category_id BIGINT;

ALTER TABLE products
ADD CONSTRAINT fk_product_category
FOREIGN KEY (category_id)
REFERENCES category(id);