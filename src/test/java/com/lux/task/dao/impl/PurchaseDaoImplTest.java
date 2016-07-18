package com.lux.task.dao.impl;

import com.lux.task.dao.models.Purchase;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PurchaseDaoImplTest extends AbstractDaoTest{

    @Autowired
    private PurchaseDaoImpl dao;

    private Random random = new Random();
    private Date now = new Date();

    @Autowired
    private ProductDaoImpl productDao;
    @Rollback
    @Test
    public void saveAndGetByIdTest(){
        Purchase purchase = getTestPurchase();
        int id = dao.save(purchase);
        purchase.setId(id);
        Purchase purchaseById = dao.getById(id);
        Date expected = purchase.getPurchaseDate();
        Date actual = purchaseById.getPurchaseDate();
        assertEquals(expected, actual);

    }
    @Rollback
    @Test
    public void saveBatchAndGetAllTest() {
        int batchSize = 5;
        List<Purchase> purchases = getListPurchases(batchSize);
        List<Purchase> allBefore = dao.getAll();
        dao.saveBatch(purchases);
        List<Purchase> allAfter = dao.getAll();
        assertEquals(allBefore.size(), allAfter.size() - batchSize);
        for (Purchase purchase : purchases) {
            int frequency = Collections.frequency(allAfter, purchase);
            assertEquals(1, frequency);
        }
    }



    private ArrayList<Purchase> getListPurchases(int count) {
        ArrayList<Purchase> purchases = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            purchases.add(getTestPurchase());
        }
        return purchases;
    }

    private Date getRandDate()  {
        return new Date(now.getTime() - (long) (random.nextDouble() * 31536000000000L));//31536000000000L = 1000 years in ms
    }

    private Purchase getTestPurchase(){
        return new Purchase(getTestProductAndSaveIt(),1234567890, getRandDate());
    }


}
