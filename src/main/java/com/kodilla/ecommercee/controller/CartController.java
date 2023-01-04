package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/cart")
public class CartController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void cartCreation(@RequestBody ProductDto productDto){

    }
    @GetMapping(value = "{cartId}")
    public CartDto getCartElements(@PathVariable Long cartId){
        return null;
    }
    @PutMapping(value ="add")
    public void addProductToCart(@RequestParam Long productId){

    }
    @PutMapping(value ="delete")
    public void deleteProductFromCard(@RequestParam Long productId){

    }
    @PutMapping(value ="order")
    public void makeOrderFromCart(){

    }
}
