package com.lux.task.dao;

import com.lux.task.dao.models.Purchase;

import java.util.List;

public interface PurchaseDao {
    public boolean storePurchase(Purchase purchase);
    public List<Purchase> storePurchases(List<Purchase> purchases);
}
