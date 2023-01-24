package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.NoProductsInCartException;
import com.kodilla.ecommercee.controller.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.NewCartDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.ProductService;
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
    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;
    private final CartMapper cartMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> cartCreation(@RequestBody NewCartDto newCartDto) throws UserNotFoundException {
        Cart newCart = cartMapper.mapToNewCart(newCartDto);
        cartService.saveCart(newCart);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<CartDto> getCartElements(@PathVariable Long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(cartMapper.mapToCartDto(cartService.getCart(cartId)));
    }

    @PutMapping(value = "{cartId}/{productId}")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartService.getCart(cartId);
        Product product = productService.getById(productId);
        cart.getProducts().add(product);
        cartService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{cartId}/{productId}")
    public ResponseEntity<Void> deleteProductFromCard(@PathVariable Long cartId, @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartService.getCart(cartId);
        Product product = productService.getById(productId);
        cart.getProducts().remove(product);
        cartService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "order/{cartId}")
    public ResponseEntity<Void> makeOrderFromCart(@PathVariable Long cartId) throws CartNotFoundException, NoProductsInCartException {
        Cart cart = cartService.getCart(cartId);
        if(cart.getProducts().isEmpty()){
           throw new NoProductsInCartException();
        }else{
            Order order = new Order(cart.getProducts(), cart.getUser());
            orderService.saveOrder(order);
            List<Product> emptyList = new ArrayList<>();
            cart.setProducts(emptyList);
            cartService.saveCart(cart);
            return ResponseEntity.ok().build();
        }
    }
}
