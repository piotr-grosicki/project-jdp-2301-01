package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product updateProduct(Product product) {
        Product toUpdate = getById(product.getProductId());
        toUpdate.setName(product.getName());
        toUpdate.setDescription(product.getDescription());
        toUpdate.setGroup(product.getGroup());
        toUpdate.setPrice(product.getPrice());
        toUpdate.setCarts(product.getCarts());
        toUpdate.setOrders(product.getOrders());

        productRepository.save(toUpdate);
        return toUpdate;
    }

    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }
}
