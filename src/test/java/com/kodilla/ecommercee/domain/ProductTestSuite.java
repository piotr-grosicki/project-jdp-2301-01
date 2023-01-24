package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.kodilla.ecommercee.domain.OrderStatus.CREATED;
import static com.kodilla.ecommercee.domain.OrderStatus.PAID;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void getProductsByIdTest() {
        //GIVEN
        Product productA = new Product("ProductA", "ProductA Description", new BigDecimal(50.00));
        Product productB = new Product("ProductB", "ProductB Description", new BigDecimal(150.00));
        productRepository.save(productA);
        productRepository.save(productB);
        Long id1 = productA.getProductId();
        Long id2 = productB.getProductId();
        //WHEN
        Product productAId1 = productRepository.findById(id1).orElse(new Product());
        Product productBId2 = productRepository.findById(id2).orElse(new Product());
        //THEN
        assertEquals(productA.getName(), productAId1.getName());
        assertEquals(productB.getName(), productBId2.getName());
        //CLEAN UP
        productRepository.deleteById(id1);
        productRepository.deleteById(id2);
    }

    @Test
    public void createProductTest(){
        //GIVEN
        Product productC = new Product();
        Group group1 = new Group("Group1", "Description1");
        productC.setGroup(group1);
        //WHEN
        productRepository.save(productC);
        groupRepository.save(group1);
        //THEN
        assertTrue(productRepository.findById(productC.getProductId()).isPresent());
        //CLEAN UP
        productRepository.deleteById(productC.getProductId());
        groupRepository.deleteById(group1.getGroupId());
    }

    @Test
    public void updateProductTest(){
        //GIVEN
        Product productD = new Product("ProductD", "ProductD Description", new BigDecimal(70.00));
        productRepository.save(productD);
        //WHEN
        Long id3 = productD.getProductId();
        Product updatedProductD = productRepository.findById(id3).orElse(new Product());
        updatedProductD.setPrice(new BigDecimal(99.00));
        productRepository.save(updatedProductD);
        //THEN
        System.out.println(productRepository.findById(id3).get().getPrice());
        assertEquals(new BigDecimal(99.00), productRepository.findById(id3).get().getPrice());
        //CLEANUP
        productRepository.deleteAll();
    }

    @Test
    public void deleteProductTest(){
        //GIVEN
        Product productE = new Product("ProductE", "ProductE Description", new BigDecimal(15.50));
        productRepository.save(productE);
        //WHEN
        Long id4 = productE.getProductId();
        productRepository.deleteById(id4);
        //THEN
        assertEquals(0,productRepository.findAll().size());
        assertFalse(productRepository.findById(id4).isPresent());
        //CLEAN UP
        productRepository.deleteAll();
    }

    @Test
    public void getProductsTest() {
        //GIVEN
        Product productF = new Product("ProductF", "ProductF Description", new BigDecimal(30.00));
        Product productG = new Product("ProductG", "ProductG Description", new BigDecimal(75.00));
        Product productH = new Product("ProductH", "ProductH Description", new BigDecimal(99.00));
        productRepository.save(productF);
        productRepository.save(productG);
        productRepository.save(productH);
        Long id5 = productF.getProductId();
        Long id6 = productG.getProductId();
        Long id7 = productH.getProductId();
        //WHEN
        List<Product> allProducts = productRepository.findAll();
        //THEN
        assertEquals(3, allProducts.size());
        //CLEAN UP
        productRepository.deleteById(id5);
        productRepository.deleteById(id6);
        productRepository.deleteById(id7);
    }

    @Test
    public void manyProductsInManyCartsTest(){
        //GIVEN
        Product productI = new Product("ProductI", "ProductI Description", new BigDecimal(10.00));
        Product productJ = new Product("ProductJ", "ProductJ Description", new BigDecimal(20.00));
        Product productK = new Product("ProductK", "ProductK Description", new BigDecimal(30.00));
        productRepository.save(productI);
        productRepository.save(productJ);
        productRepository.save(productK);
        //WHEN
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        cart1.getProducts().add(productI);
        cart1.getProducts().add(productK);
        cart2.getProducts().add(productJ);
        cart2.getProducts().add(productI);
        cart2.getProducts().add(productI);
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        //THEN
        int testProductsInCart1 = cart1.getProducts().size();
        int testProductsInCart2 = cart2.getProducts().size();
        assertEquals(2, testProductsInCart1);
        assertEquals(3, testProductsInCart2);
        //CLEANUP
        productRepository.deleteAll();
        cartRepository.deleteAll();
    }
    @Test
    public void manyProductsInManyOrdersTest(){
        //GIVEN
        Product productL = new Product("ProductI", "ProductI Description", new BigDecimal(10.00));
        Product productM = new Product("ProductJ", "ProductJ Description", new BigDecimal(20.00));
        productRepository.save(productL);
        productRepository.save(productM);
        //WHEN
        Order order1 = new Order();
        Order order2 = new Order();
        order1.getOrderedProducts().add(productL);
        order1.getOrderedProducts().add(productM);
        order2.getOrderedProducts().add(productL);
        order2.getOrderedProducts().add(productM);
        orderRepository.save(order1);
        orderRepository.save(order2);
        //THEN
        int testProductsInOrder1 = order1.getOrderedProducts().size();
        int testProductsInOrder2 = order2.getOrderedProducts().size();
        assertEquals(2, testProductsInOrder1);
        assertEquals(2, testProductsInOrder2);
        //CLEANUP
        orderRepository.deleteAll();
        productRepository.deleteAll();
    }
}
