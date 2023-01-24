package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.domain.dto.NewGroupDto;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupMapper {

    ProductRepository productRepository;

    public Group mapToGroup(final GroupDto groupDto) {
        return new Group(
                groupDto.getGroupId(),
                groupDto.getGroupName(),
                groupDto.getGroupDescription(),
                findAllById(groupDto.getProductsId()));
    }

    public GroupDto mapToGroupDto(Group group) {
        return new GroupDto(
                group.getGroupId(),
                group.getGroupName(),
                group.getGroupDescription(),
                group.getProducts().isEmpty() ? Collections.emptyList() : group.getProducts().stream()
                        .map(Product::getProductId)
                        .collect(Collectors.toList())
        );

    }

    public List<GroupDto> mapToGroupsDto(List<Group> groups) {
        return groups.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }

    public List<Product> findAllById(List<Long> productsId) {
        List<Product> results = new ArrayList<>();
        if (Objects.nonNull(productsId)) {
            for (Long id : productsId) {
                productRepository.findById(id).ifPresent(results::add);
            }
        }
        return results;
    }

    public Group mapToNewGroup(NewGroupDto newGroupDto) {
        return new Group(
                newGroupDto.getGroupName(),
                newGroupDto.getGroupDescription(),
                findAllById(newGroupDto.getProductsId()));
    }
}
