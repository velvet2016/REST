package com.lux.task.dao.services;

import com.lux.task.dao.impl.PurchaseDaoImpl;
import com.lux.task.dao.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseService {

    @Autowired
    private PurchaseDaoImpl dao;

    public boolean storePurchase(Purchase purchase){
        return dao.storePurchase(purchase);
    }

    public List<Purchase> storePurchases(List<Purchase> purchases) {
        return dao.storePurchases(purchases);
    }
}
