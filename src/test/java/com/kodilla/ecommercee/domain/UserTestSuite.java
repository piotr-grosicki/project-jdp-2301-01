package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static com.kodilla.ecommercee.domain.OrderStatus.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void getUserByIdTest() {
        //GIVEN
        User userA = new User("UserA");
        User userB = new User("UserB");
        userRepository.save(userA);
        userRepository.save(userB);
        Long id1 = userA.getUserId();
        Long id2 = userB.getUserId();
        //WHEN
        User userAId1 = userRepository.findById(id1).orElse(new User());
        User userBId2 = userRepository.findById(id2).orElse(new User());
        //THEN
        assertEquals(userA.getUserName(), userAId1.getUserName());
        assertEquals(userB.getUserName(), userBId2.getUserName());
        //CLEAN UP
        userRepository.deleteById(id1);
        userRepository.deleteById(id2);
    }

    @Test
    public void createUserTest(){
        //GIVEN
        User userC = new User();
        Cart cart1 = new Cart();
        userC.setCart(cart1);
        //WHEN
        userRepository.save(userC);
        cartRepository.save(cart1);
        //THEN
        assertTrue(userRepository.findById(userC.getUserId()).isPresent());
        //CLEAN UP
        userRepository.deleteById(userC.getUserId());
        cartRepository.deleteById(cart1.getCartId());
    }

    @Test
    public void updateUserTest(){
        //GIVEN
        User userD = new User("UserD");
        userRepository.save(userD);
        //WHEN
        Long id3 = userD.getUserId();
        User updatedUserD = userRepository.findById(id3).orElse(new User());
        updatedUserD.setUserName("User VIP");
        userRepository.save(updatedUserD);
        //THEN
        System.out.println(userRepository.findById(id3).get().getUserName());
        assertEquals("User VIP", userRepository.findById(id3).get().getUserName());
        //CLEANUP
        userRepository.deleteAll();
    }

    @Test
    public void deleteUserTest(){
        //GIVEN
        User userE = new User("UserE");
        userRepository.save(userE);
        //WHEN
        Long id4 = userE.getUserId();
        userRepository.deleteById(id4);

        //THEN
        assertEquals(0,userRepository.findAll().size());
        assertFalse(userRepository.findById(id4).isPresent());

        //CLEAN UP
        userRepository.deleteAll();
    }

    @Test
    public void getUsersTest() {
        //GIVEN
        User userF = new User("UserF");
        User userG = new User("UserG");
        User userH = new User("UserH");
        userRepository.save(userF);
        userRepository.save(userG);
        userRepository.save(userH);
        Long id5 = userF.getUserId();
        Long id6 = userG.getUserId();
        Long id7 = userH.getUserId();
        //WHEN
        List<User> allUsers = userRepository.findAll();
        //THEN
        assertEquals(3, allUsers.size());
        //CLEAN UP
        userRepository.deleteById(id5);
        userRepository.deleteById(id6);
        userRepository.deleteById(id7);
    }

    @Test
    public void testAddOrdersToUser() {
        //GIVEN
        User userI = new User("UserI");
        userRepository.save(userI);
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        userI.getOrders().add(order1);
        userI.getOrders().add(order2);
        userI.getOrders().add(order3);
        Long id8 = userI.getUserId();
        //WHEN
        int oneUserManyOrders = userI.getOrders().size();
        //THEN
        assertEquals(3, oneUserManyOrders);
        //CLEAN UP
        userRepository.deleteById(id8);
        orderRepository.deleteAll();
    }

}
