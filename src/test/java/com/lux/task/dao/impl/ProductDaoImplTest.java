package com.lux.task.dao.impl;

import com.lux.task.dao.models.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
@WebAppConfiguration
@Transactional
public class ProductDaoImplTest {


    @Qualifier("productDaoImpl")
    @Autowired
    private ProductDaoImpl dao;


    @Rollback
    @Test()
    public void saveAndGetByIdTest(){
        Product product = getTestProduct();
        int id = dao.save(product);
        Product productById = dao.getById(id);
        assertEquals(product,productById);
    }

    @Rollback
    @Test(expected = DuplicateKeyException.class)
    public void productNameUniqueConstraintTest(){
        Product product = getTestProduct();
        dao.save(product);
        dao.save(product);
    }

    @Rollback
    @Test()
    public void getAllTest(){
        List<Product> allBefore = dao.getAll();
        Product testProduct = getTestProduct();
        dao.save(testProduct);
        List<Product> allAfter = dao.getAll();
        allAfter.removeAll(allBefore);
        assertEquals(Arrays.asList(testProduct), allAfter);
    }

    @Rollback
    @Test()
    public void getByNameTest(){
        Product product = getTestProduct();
        dao.save(product);
        Product productByName = dao.getByName(product.getName());
        assertEquals(product,productByName);
    }



    public static Product getTestProduct() {
        Product product = new Product();
        long rand = new Random().nextLong();
        product.setName("this is test product created for purpose of integration testing ProductDaoImpl. " +
                "If you see this product  it means tests have not managed to rollback"+rand);
        product.setPrice(111.11);
        return product;
    }
}
