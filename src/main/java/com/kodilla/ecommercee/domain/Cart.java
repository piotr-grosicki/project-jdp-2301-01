package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "CART_ID", unique = true)
    private Long cartId;
}