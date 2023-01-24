package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class CartTestSuite {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void createCartTest() {
        //GIVEN
        Cart cart = new Cart();
        User user = new User("ExampleName");
        cart.setUser(user);
        //WHEN
        userRepository.save(user);
        cartRepository.save(cart);

        //THEN
        assertTrue(cartRepository.findById(cart.getCartId()).isPresent());

        //CLEAN UP
        cartRepository.deleteById(cart.getCartId());
        userRepository.deleteById(user.getUserId());
    }

    @Test
    public void addProductsToCartTest() {
        //GIVEN
        Product product1 = new Product("ExName", "ExDescription", new BigDecimal(10));
        Cart cart = new Cart();
        User user = new User("ExampleName");
        cart.setUser(user);

        //WHEN
        productRepository.save(product1);
        cart.getProducts().add(product1);
        userRepository.save(user);
        cartRepository.save(cart);

        cartRepository.findById(cart.getCartId());

        //THEN
        assertEquals(1, cartRepository.findById(cart.getCartId()).get().getProducts().size());

        //CLEAN UP
        cartRepository.deleteById(cart.getCartId());
        userRepository.deleteById(user.getUserId());
        productRepository.deleteAll();
    }

    @Test
    public void getAllProductsInTheCartTest() {
        //GIVEN
        Product product1 = new Product("ExName", "ExDescription", new BigDecimal(10));
        Product product2 = new Product("ExName2", "ExDescription2", new BigDecimal(20));
        Product product3 = new Product("ExName3", "ExDescription3", new BigDecimal(30));
        Cart cart = new Cart();
        User user = new User("ExampleName");
        cart.setUser(user);

        //WHEN
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cart.getProducts().add(product3);
        userRepository.save(user);
        cartRepository.save(cart);

        cartRepository.findById(cart.getCartId());

        //THEN
        assertEquals(3, cartRepository.findById(cart.getCartId()).get().getProducts().size());

        //CLEAN UP
        cartRepository.deleteById(cart.getCartId());
        userRepository.deleteById(user.getUserId());
        productRepository.deleteAll();
    }


    @Test
    public void deleteProductInCartByIdTest() {
        //GIVEN
        Product product1 = new Product("ExName", "ExDescription", new BigDecimal(10));
        Product product2 = new Product("ExName2", "ExDescription2", new BigDecimal(20));
        Product product3 = new Product("ExName3", "ExDescription3", new BigDecimal(30));
        Cart cart = new Cart();
        User user = new User("ExampleName");
        cart.setUser(user);

        //WHEN
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cart.getProducts().add(product3);
        userRepository.save(user);
        cartRepository.save(cart);
        cartRepository.findById(cart.getCartId());


        //THEN
        assertEquals(3, cartRepository.findById(cart.getCartId()).get().getProducts().size());

        //CLEAN UP
        cartRepository.deleteById(cart.getCartId());
        userRepository.deleteById(user.getUserId());
        productRepository.deleteAll();
    }

    @Test
    public void createOrderBasedOnCart() {
        //GIVEN
        Product product1 = new Product("ExName", "ExDescription", new BigDecimal(10));
        Product product2 = new Product("ExName2", "ExDescription2", new BigDecimal(20));
        Product product3 = new Product("ExName3", "ExDescription3", new BigDecimal(30));
        Cart cart = new Cart();
        User user = new User("ExampleName");
        cart.setUser(user);

        //WHEN
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cart.getProducts().add(product3);
        userRepository.save(user);
        cartRepository.save(cart);
        //
        List<Product> clonedCart = new ArrayList<>();
        for (Product product : cart.getProducts()) {
            clonedCart.add(product);
        }
        Order orderFromCart = new Order(clonedCart,user);
        orderRepository.save(orderFromCart);
        //THEN
        assertTrue(orderRepository.findById(orderFromCart.getOrderId()).isPresent());
        assertEquals(3,orderRepository.findById(orderFromCart.getOrderId()).get().getOrderedProducts().size());

        //CLEAN UP
        cartRepository.deleteById(cart.getCartId());
        userRepository.deleteById(user.getUserId());
        productRepository.deleteById(product1.getProductId());
        productRepository.deleteById(product2.getProductId());
        productRepository.deleteById(product3.getProductId());
        orderRepository.deleteById(orderFromCart.getOrderId());

    }

}
