package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.NewProductDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public Product mapToProduct(ProductDto productDto) {
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
        //toDo add cart mapping when CartService is ready
        return Collections.emptyList();
    }

    private List<Order> mapToOrders(List<Long> ordersIds) {
        //toDo add order mapping when OrderService is ready
        return Collections.emptyList();
    }

    private Group mapToGroup(Long groupId) {
        //toDo add group mapping when GroupService is ready
        return null;
    }
}
