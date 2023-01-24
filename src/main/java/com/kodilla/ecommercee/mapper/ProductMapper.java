package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.NewProductDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    OrderRepository orderRepository;
    CartRepository cartRepository;
    GroupRepository groupRepository;

    public Product mapToNewProduct(NewProductDto newProductDto) throws GroupNotFoundException {
        return new Product(newProductDto.getName(), newProductDto.getDescription(),groupRepository.findById(newProductDto.getGroupId()).orElseThrow(GroupNotFoundException::new),newProductDto.getPrice());
    }

    public List<ProductDto> mapToProductDtoList(List<Product> products) {
        return products.stream()
                .map(p -> new ProductDto(
                        p.getProductId(),
                        p.getName(),
                        p.getDescription(),
                        p.getGroup().getGroupId(),
                        p.getPrice(),
                        mapToCartsIds(p.getCarts()),
                        mapToOrdersIds(p.getOrders())
                ))
                .collect(Collectors.toList());
    }

    private List<Long> mapToOrdersIds(List<Order> orders) {
        return orders.stream()
                .map(Order::getOrderId)
                .collect(Collectors.toList());
    }

    private List<Long> mapToCartsIds(List<Cart> carts) {
        return carts.stream()
                .map(Cart::getCartId)
                .collect(Collectors.toList());
    }

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getGroup().getGroupId(),
                product.getPrice(),
                mapToCartsIds(product.getCarts()),
                mapToOrdersIds(product.getOrders()));
    }

    public Product mapToProduct(ProductDto productDto) throws GroupNotFoundException {
        return new Product(
                productDto.getProductId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                mapToCarts(productDto.getCartsIds()),
                mapToOrders(productDto.getOrdersIds()),
                mapToGroup(productDto.getGroupId())
        );
    }

    private List<Cart> mapToCarts(List<Long> cartsIds) {
        List<Cart> results = new ArrayList<>();
        if (Objects.nonNull(cartsIds)) {
            for (Long id : cartsIds) {
                cartRepository.findById(id).ifPresent(results::add);
            }
        }
        return results;
    }

    private List<Order> mapToOrders(List<Long> ordersIds) {
        List<Order> results = new ArrayList<>();
        if (Objects.nonNull(ordersIds)) {
            for (Long id : ordersIds) {
                orderRepository.findById(id).ifPresent(results::add);
            }
        }
        return results;
    }

    private Group mapToGroup(Long groupId) throws GroupNotFoundException {
       return groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
    }
}
