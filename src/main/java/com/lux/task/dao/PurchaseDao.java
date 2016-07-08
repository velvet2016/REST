package com.lux.task.dao;

import com.lux.task.dao.models.Purchase;

public interface PurchaseDao {
    public boolean storePurchase(Purchase purchase);
}
