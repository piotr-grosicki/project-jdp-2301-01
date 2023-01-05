package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {
    private long userId;
    private String userName;
    private Cart cart;
    private List<Order> orders;

    public User(String userName) {
        this.userName = userName;
    }

    public User() {
    }

    @Id
    @GeneratedValue
    @NotNull
    public long getId() {
        return userId;
    }

    public void setId(long userId) {
        this.userId = userId;
    }

    @NotNull
    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    @OneToMany(targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
