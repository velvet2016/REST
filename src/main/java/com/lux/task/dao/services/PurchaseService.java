package com.lux.task.dao.services;

import com.lux.task.dao.PurchaseDao;
import com.lux.task.dao.models.Purchase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Purchase> getAll() {
        return dao.getAll();
    }
}
