package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class OrderTestSuite {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void createOrderTest() {
        //GIVEN
        Order order = new Order();
        User user = new User("TEST USER");
        order.setUser(user);

        //WHEN
        userRepository.save(user);
        orderRepository.save(order);

        //THEN
        assertTrue(orderRepository.findById(order.getOrderId()).isPresent());

        //CLEAN UP
        orderRepository.deleteById(order.getOrderId());
        userRepository.deleteById(user.getUserId());
    }

    @Test
    public void addProductToOrderTest() {
        //GIVEN
        Product product = new Product();
        Order order = new Order();
        User user = new User("TEST USER");
        order.setUser(user);

        //WHEN
        productRepository.save(product);
        order.getOrderedProducts().add(product);
        userRepository.save(user);
        orderRepository.save(order);
        int productQuantity = order.getOrderedProducts().size();

        //THEN
        assertEquals(1, productQuantity);

        //CLEAN UP
        orderRepository.deleteById(order.getOrderId());
        userRepository.deleteById(user.getUserId());
        productRepository.deleteById(product.getProductId());
    }
}
