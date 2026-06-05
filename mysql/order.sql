use market_Data ; 

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_date DATETIME NOT NULL,
    total_price DECIMAL(12,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    user_id BIGINT NOT NULL,

    CONSTRAINT fk_order_user
    FOREIGN KEY (user_id)
    REFERENCES users(id)
);
CREATE TABLE order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    price DECIMAL(12,2) NOT NULL,

    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    CONSTRAINT fk_orderitem_order
    FOREIGN KEY (order_id)
    REFERENCES orders(id),

    CONSTRAINT fk_orderitem_product
    FOREIGN KEY (product_id)
    REFERENCES products(id)
);
