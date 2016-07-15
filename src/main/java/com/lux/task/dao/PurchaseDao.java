package com.lux.task.dao;

import com.lux.task.dao.models.Purchase;

import java.util.List;

public interface PurchaseDao {
    public int  storePurchase(Purchase purchase);
    public Purchase getPurchaseById(int id);
    public List<Purchase> storePurchases(List<Purchase> purchases);
    public List<Purchase> getAll();
}
