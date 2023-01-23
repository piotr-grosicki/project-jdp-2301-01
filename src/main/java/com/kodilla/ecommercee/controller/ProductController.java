package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.NewProductDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/product")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>>getProducts() {
        return ResponseEntity.ok(productMapper.mapToProductDtoList(productService.getAll()));

    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.getById(productId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody NewProductDto newProductDto) throws GroupNotFoundException {
        Product product = productMapper.mapToNewProduct(newProductDto);
        productService.createProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) throws ProductNotFoundException, GroupNotFoundException {
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.updateProduct(productMapper.mapToProduct(productDto))));
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }
}
