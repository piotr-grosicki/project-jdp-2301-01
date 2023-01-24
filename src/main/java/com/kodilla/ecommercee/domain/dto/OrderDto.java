package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private long orderId;
    private OrderStatus orderStatus;
    private LocalDate orderIssued;
    private Long userId;
    private List<Long> productsId;

}
