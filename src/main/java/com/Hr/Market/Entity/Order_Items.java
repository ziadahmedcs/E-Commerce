package com.Hr.Market.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class Order_Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;
    @Column(name = "quantity")
    private  int  quantity ;
    @Column(name = "price")
    private BigDecimal price ;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders ;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products  ;

    public Order_Items(Orders orders, BigDecimal price, Products products, int quantity) {
        this.orders = orders;
        this.price = price;
        this.products = products;
        this.quantity = quantity;
    }

    public Order_Items() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
