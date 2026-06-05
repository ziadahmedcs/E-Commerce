package com.Hr.Market.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;
    @Column(name = "name")
    private String name ;
    @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL,
    orphanRemoval = true)
    List<Products> products ;

    public Category() {
    }

    public Category(String name, List<Products> products) {
        this.name = name;
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

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
