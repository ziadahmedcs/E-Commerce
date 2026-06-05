package com.Hr.Market.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders" )
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;
    @Column(name = "order_date")
    private LocalDateTime order_date ;
    @Column(name = "total_price")
    private BigDecimal total_price ;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status ;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users ;
    @OneToMany(mappedBy = "orders")
    @JsonIgnore
    private List<Order_Items> orderItemsList ;

    public Orders() {
    }

    public Orders(LocalDateTime order_date, List<Order_Items> orderItemsList, OrderStatus status, BigDecimal total_price, Users users) {
        this.order_date = order_date;
        this.orderItemsList = orderItemsList;
        this.status = status;
        this.total_price = total_price;
        this.users = users;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDateTime order_date) {
        this.order_date = order_date;
    }



    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }



    public List<Order_Items> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<Order_Items> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }
    public enum OrderStatus {
        PENDING,
        PAID,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }
}

