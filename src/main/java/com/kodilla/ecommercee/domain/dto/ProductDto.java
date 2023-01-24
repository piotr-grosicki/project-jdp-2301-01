package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private Long groupId;

    private BigDecimal price;
    private List<Long> cartsIds = new ArrayList<>();
    private List<Long> ordersIds = new ArrayList<>();

    public ProductDto(String name, String description, Long groupId, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.groupId = groupId;
        this.price = price;
    }
}
