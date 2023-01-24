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
public class NewOrderDto {
    private List<Long> productsId;
    private Long userId;
    private OrderStatus orderStatus;
    private LocalDate orderIssued;

}
