package com.lux.task.dao;

import com.lux.task.dao.models.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> getAll();
    public Product getProductByName(String name);

}
