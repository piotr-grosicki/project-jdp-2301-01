package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveGroup(){
        //GIVEN & WHEN
        Group group1 = new Group("Group1", "Description1");
        Group group2 = new Group("Group2", "Description2");
        groupRepository.save(group1);
        groupRepository.save(group2);

        //THEN
        assertFalse(groupRepository.findAll().isEmpty());

        //CLEAN UP
        groupRepository.deleteAll();
    }

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

        Group groupById1 = groupRepository.findById(id1).orElse(new Group());
        Group groupById2 = groupRepository.findById(id2).orElse(new Group());

//        System.out.println(group1.getGroupName());
//        System.out.println(group1.getGroupDescription());
//        System.out.println(groupById1.getGroupName());
//        System.out.println(groupById1.getGroupDescription());

        //THEN
        assertEquals(group1.getGroupName(), groupById1.getGroupName());
        assertEquals(group1.getGroupDescription(), groupById1.getGroupDescription());
        assertEquals(group2.getGroupName(), groupById2.getGroupName());
        assertEquals(group1.getGroupDescription(), groupById1.getGroupDescription());

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
    public void testDeleteByGroupId(){
        //GIVEN
        Group group1 = new Group("Group1", "Description1");
        Group group2 = new Group("Group2", "Description2");
        groupRepository.save(group1);
        groupRepository.save(group2);

        //WHEN
        Long id1 = group1.getGroupId();
        groupRepository.deleteById(id1);

        //THEN
        assertEquals(1,groupRepository.findAll().size());
        assertFalse(groupRepository.findById(id1).isPresent());

        //CLEAN UP
        groupRepository.deleteAll();
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
        group1.getProducts().add(product1);
        group1.getProducts().add(product2);

        Long id1 = group1.getGroupId();

        //WHEN
        int testProductsInGroupSize = group1.getProducts().size();

        //THEN
        assertEquals(2, testProductsInGroupSize);
        //CLEAN UP
        groupRepository.deleteById(id1);
        productRepository.deleteAll();

    }
    @Test
    public void testUpdateGroupData(){
        //GIVEN
        Group group1 = new Group("Group1", "Description1");
        groupRepository.save(group1);

        //WHEN
        Long id1 = group1.getGroupId();
        Group groupById1 = groupRepository.findById(id1).orElse(new Group());
        groupById1.setGroupName("ChangedName");
        groupRepository.save(groupById1);

        //THEN
        System.out.println(groupRepository.findById(id1).get().getGroupName());
        assertEquals("ChangedName", groupRepository.findById(id1).get().getGroupName());
        //CLEANUP
        groupRepository.deleteAll();
    }

}