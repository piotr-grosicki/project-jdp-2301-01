package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.NoProductsInCartException;
import com.kodilla.ecommercee.controller.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.OrderDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/cart")
public class CartController {
    private final CartDbService cartDbService;
    private final ProductDbService productDbService;
    private final OrderDbService orderDbService;
    private final CartMapper cartMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> cartCreation(@RequestBody CartDto cartDto) throws UserNotFoundException {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartDbService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<CartDto> getCartElements(@PathVariable Long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(cartMapper.mapToCartDto(cartDbService.getCart(cartId)));
    }

    @PutMapping(value = "{cartId}/{productId}")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartDbService.getCart(cartId);
        Product product = productDbService.getProduct(productId);
        cart.getProducts().add(product);
        cartDbService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{cartId}/{productId}")
    public ResponseEntity<Void> deleteProductFromCard(@PathVariable Long cartId, @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartDbService.getCart(cartId);
        Product product = productDbService.getProduct(productId);
        cart.getProducts().remove(product);
        cartDbService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "order/{cartId}")
    public ResponseEntity<Void> makeOrderFromCart(@PathVariable Long cartId) throws CartNotFoundException, NoProductsInCartException {
        Cart cart = cartDbService.getCart(cartId);
        if(cart.getProducts().isEmpty()){
           throw new NoProductsInCartException();
        }else{
            Order order = new Order(cart.getProducts(), cart.getUser());
            orderDbService.saveOrder(order);
            List<Product> emptyList = new ArrayList<>();
            cart.setProducts(emptyList);
            cartDbService.saveCart(cart);
            return ResponseEntity.ok().build();
        }
    }
}
