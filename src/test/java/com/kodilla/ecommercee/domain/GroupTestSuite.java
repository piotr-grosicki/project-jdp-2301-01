package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class GroupTestSuite {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testGetGroupById() {
        //GIVEN
        Group group1 = new Group("Group1", "Description1");
        Group group2 = new Group("Group2", "Description2");
        groupRepository.save(group1);
        groupRepository.save(group2);
        Long id1 = group1.getGroupId();
        Long id2 = group2.getGroupId();

        //WHEN
        Optional<Group> groupById1 = groupRepository.findById(id1);
        Optional<Group> groupById2 = groupRepository.findById(id2);

        //THEN
        assertEquals(group1, groupById1);
        assertEquals(group2, groupById2);

        //CLEAN UP
        groupRepository.deleteById(id1);
        groupRepository.deleteById(id2);
    }

    @Test
    public void testFindAllGroup() {
        //GIVEN
        Group group1 = new Group("Group1", "Description1");
        Group group2 = new Group("Group2", "Description2");
        groupRepository.save(group1);
        groupRepository.save(group2);
        Long id1 = group1.getGroupId();
        Long id2 = group2.getGroupId();

        //WHEN
        List<Group> testGroups = groupRepository.findAll();

        //THEN
        assertEquals(2, testGroups.size());
        //CLEAN UP
        groupRepository.deleteById(id1);
        groupRepository.deleteById(id2);

    }

    @Test
    public void testAddProductToGroup() {
        //GIVEN

        Group group1 = new Group("Group1", "Description1");
        groupRepository.save(group1);

        Product product1 = new Product("Product1", "Description1", new BigDecimal(100));
        Product product2 = new Product("Product2", "Description2", new BigDecimal(100));
        productRepository.save(product1);
        productRepository.save(product2);

        Long id1 = group1.getGroupId();
        Long idProduct1 = product1.getProductId();
        Long idProduct2 = product2.getProductId();
        //WHEN
        int testProductsInGroupSize = group1.getProducts().size();

        //THEN
        assertEquals(2, testProductsInGroupSize);
        //CLEAN UP
        groupRepository.deleteById(id1);
        productRepository.deleteById(idProduct1);
        productRepository.deleteById(idProduct2);
    }
}