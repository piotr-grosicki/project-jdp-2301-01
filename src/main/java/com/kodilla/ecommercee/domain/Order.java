package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    private Long orderId;

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "orders")
    private List<Product> orderedProducts = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS")
    private OrderStatus orderStatus;

    @NotNull
    @Column(name = "CREATION_DATE")
    private LocalDate orderCreated;

    public Order(List<Product> orderedProducts, User user, OrderStatus orderStatus, LocalDate orderCreated) {
        this.orderedProducts = orderedProducts;
        this.user = user;
        this.orderStatus = orderStatus;
        this.orderCreated = orderCreated;
    }
    public Order(List<Product> orderedProducts, User user) {
        this.orderedProducts = orderedProducts;
        this.user = user;
        orderStatus = OrderStatus.CREATED;
        orderCreated = LocalDate.now();
    }
    public Order(User user, OrderStatus orderStatus, LocalDate orderCreated) {
        this.user = user;
        this.orderStatus = orderStatus;
        this.orderCreated = orderCreated;
    }
}