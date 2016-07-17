package com.lux.task.dao;

import com.lux.task.dao.models.Purchase;

import java.util.List;

public interface PurchaseDao {
    public int save(Purchase purchase);
    public Purchase getById(int id);
    public List<Purchase> saveBatch(List<Purchase> purchases);
    public List<Purchase> getAll();
}
