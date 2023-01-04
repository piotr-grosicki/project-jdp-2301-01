package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.controller.OrderStatus;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private long orderId;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
    private LocalDate orderIssued;
    private Cart cart;
    private User user;
    private List<Product> products;
}
