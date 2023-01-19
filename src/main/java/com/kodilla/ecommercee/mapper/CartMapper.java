package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CartMapper {

    CartRepository cartRepository;
    UserRepository userRepository;
    ProductRepository productRepository;

    public Cart mapToCart(CartDto cartDto) throws UserNotFoundException {
        return new Cart(
                cartDto.getCartId(),
                userRepository.findById(cartDto.getUserId()).orElseThrow(UserNotFoundException::new),
                findAllById(cartDto.getProductsId()));
    }

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(
                cart.getCartId(),
                cart.getUser().getUserId(),
                cart.getProducts().isEmpty() ? Collections.emptyList() : cart.getProducts().stream()
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
}