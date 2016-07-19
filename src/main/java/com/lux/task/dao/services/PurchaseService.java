package com.lux.task.dao.services;

import com.lux.task.dao.PurchaseDao;
import com.lux.task.dao.impl.PurchaseDaoImpl;
import com.lux.task.dao.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseDao dao;

    public int storePurchase(Purchase purchase){
        return dao.save(purchase);
    }

    public List<Purchase> storePurchases(List<Purchase> purchases) {
        return dao.saveBatch(purchases);
    }
}
