package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.controller.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderDto {
    private long orderId;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
    private LocalDate orderIssued;
    private Cart cart;
    private User user;
    private List<Product> products;
}
