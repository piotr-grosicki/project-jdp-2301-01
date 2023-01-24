package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.exceptions.CartNotFoundExceptions;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    private OrderRepository orderRepository;
    private CartRepository cartRepository;

    public User mapToUser(UserDto userDto) throws CartNotFoundExceptions {
        return new User(
                userDto.getUserId(),
                userDto.getUserName(),
                cartRepository.findById(userDto.getCartId()).orElseThrow(CartNotFoundExceptions::new),
                findAllById(userDto.getOrdersId())
        );
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getUserName(),
                user.getCart().getCartId(),
                mapToOrdersIds(user.getOrders())
        );
    }

    public List<UserDto> mapToUsersDto(List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    public List<Order> findAllById(List<Long> ordersId) {
        List<Order> results = new ArrayList<>();
        if (Objects.nonNull(ordersId)) {
            for (Long id : ordersId) {
                orderRepository.findById(id).ifPresent(results::add);
            }
        }
        return results;
    }

    private List<Long> mapToOrdersIds(List<Order> orders) {
        return orders.stream()
                .map(Order::getOrderId)
                .collect(Collectors.toList());
    }
}
