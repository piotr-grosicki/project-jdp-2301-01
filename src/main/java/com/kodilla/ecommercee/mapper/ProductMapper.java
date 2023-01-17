package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.NewProductDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {
    public Product mapToNewProduct(NewProductDto newProductDto) {
        //toDo add group mapping when GroupService is ready/done
        return new Product(newProductDto.getName(), newProductDto.getDescription(), newProductDto.getPrice());
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
                .map(c -> c.getOrderId())
                .collect(Collectors.toList());
    }

    private List<Long> mapToCartsIds(List<Cart> carts) {
        return carts.stream()
                .map(c -> c.getCartId())
                .collect(Collectors.toList());
    }
}
