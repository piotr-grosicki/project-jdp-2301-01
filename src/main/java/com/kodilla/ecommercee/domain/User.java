package com.kodilla.ecommercee.domain;

<<<<<<< HEAD
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
=======
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
>>>>>>> origin/JDP230101-15

    @Id
    @GeneratedValue
    @NotNull
<<<<<<< HEAD
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
=======
    @Column(name = "USER_ID", unique = true)
    private long userId;

    @NotNull
    @Column(name = "USER_NAME")
    private String userName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = " JOIN_USER_CART",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")}
    )
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = " JOIN USER_ORDER",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")}
    )
    private List<Order> orders;

}
>>>>>>> origin/JDP230101-15
