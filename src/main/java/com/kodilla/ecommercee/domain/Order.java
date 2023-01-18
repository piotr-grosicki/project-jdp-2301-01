package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;

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
            fetch = FetchType.EAGER,
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

    public Order(List<Product> orderedProducts){
        this.orderedProducts = orderedProducts;
        orderStatus = OrderStatus.CREATED;
        orderCreated = LocalDate.now();
    }

}