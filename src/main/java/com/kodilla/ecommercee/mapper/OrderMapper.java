package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderMapper {
    UserRepository userRepository;
    ProductRepository productRepository;


    public Order mapToOrder(OrderDto orderDto) throws UserNotFoundException {
        return new Order(
                orderDto.getOrderId(),
                findAllById(orderDto.getProductsId()),
                userRepository.findById(orderDto.getUserId()).orElseThrow(UserNotFoundException::new),
                orderDto.getOrderStatus(),
                orderDto.getOrderIssued()
        );
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getOrderStatus(),
                order.getOrderCreated(),
                order.getUser().getUserId(),
                order.getOrderedProducts().isEmpty() ? Collections.emptyList() : order.getOrderedProducts().stream()
                        .map(Product::getProductId)
                        .collect(Collectors.toList())
        );
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

    public List<OrderDto> mapToOrdersDto(List<Order> orders) {
        return orders.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
