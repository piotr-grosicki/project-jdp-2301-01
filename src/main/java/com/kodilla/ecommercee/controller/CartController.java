package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/cart")
public class CartController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void cartCreation(@RequestBody CartDto CartDto){

    }
    @GetMapping(value = "{cartId}")
    public CartDto getCartElements(@PathVariable Long cartId){
        return null;
    }
    @PutMapping(value ="{cartId}/{productId}")
    public void addProductToCart(@PathVariable Long cartId, @PathVariable Long productId){

    }
    @DeleteMapping(value ="{cartId}/{productId}")
    public void deleteProductFromCard(@PathVariable Long cartId, @PathVariable Long productId){

    }
    @PostMapping(value ="order/{cartId}")
    public void makeOrderFromCart(@PathVariable Long cartId){

    }
}
