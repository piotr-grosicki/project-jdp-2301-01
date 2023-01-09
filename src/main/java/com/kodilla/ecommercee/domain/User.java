package com.kodilla.ecommercee.domain;

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

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "PRODUCT_ID", unique = true)
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