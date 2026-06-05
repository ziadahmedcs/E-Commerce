package com.Hr.Market.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;
    @Column(name = "name")
    private  String name;
    @Column (name = "price")
    private BigDecimal price ;
    @Column(name = "quantity")
    private  int quantity   ;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category ;
    @OneToMany(mappedBy = "products")
    @JsonIgnore
    private List <Order_Items> orderItemsList ;

    public Products() {
    }

    public Products(String name, BigDecimal price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Products(Category category, String name, BigDecimal price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Products(Category category, String name, List<Order_Items> orderItemsList, BigDecimal price, int quantity) {
        this.category = category;
        this.name = name;
        this.orderItemsList = orderItemsList;
        this.price = price;
        this.quantity = quantity;
    }

    public List<Order_Items> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<Order_Items> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }
}
