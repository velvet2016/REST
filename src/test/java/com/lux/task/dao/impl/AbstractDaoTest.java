package com.lux.task.dao.impl;

import com.lux.task.dao.models.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDaoTest {



    @Autowired
    protected ProductDaoImpl productDao;

    @Autowired
    protected PurchaseDaoImpl purchaseDao;

    @Autowired
    protected ReportDaoImpl reportDao;

    protected Product getTestProductAndSaveIt() {
        Product testProduct = ProductDaoImplTest.getTestProductWithoutId();
        int productId = productDao.save(testProduct);
        testProduct.setId(productId);
        return testProduct;
    }
}
