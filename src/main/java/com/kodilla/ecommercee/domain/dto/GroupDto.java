package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private Long gropuId;
    private String groupName;
    private String groupDescription;
    private List<Product> products;
}
