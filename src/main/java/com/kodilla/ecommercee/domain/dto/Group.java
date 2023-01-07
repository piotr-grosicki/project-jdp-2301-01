package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Group {
    private Long gropuId;
    private String groupName;
    private String groupDescription;
    private List<Product> products;

}
