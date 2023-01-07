package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private Long groupId;
    private String groupName;
    private String groupDescription;
    private List<ProductDto> products;
}
