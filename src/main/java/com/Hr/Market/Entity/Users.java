package com.Hr.Market.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {
    public Users() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id  ;
    @Column(name = "username")
    private  String username  ;
    @Column(name = "email")
    private  String email  ;
    @Column(name = "password")
    private  String password  ;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles" , joinColumns = @JoinColumn(name = "user_id") ,inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles>roles = new HashSet<>() ;
    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List <Orders>orders ;

    public Users(String email, List<Orders> orders, String password, Set<Roles> roles, String username) {
        this.email = email;
        this.orders = orders;
        this.password = password;
        this.roles = roles;
        this.username = username;
    }

    public Users(String email, String password, Set<Roles> roles, String username) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

}
