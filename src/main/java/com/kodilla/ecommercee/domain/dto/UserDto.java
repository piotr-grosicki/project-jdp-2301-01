package com.kodilla.ecommercee.domain.dto;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long userId;
    private String userName;
    private Cart cart;
    private List<Order> orders;
}
