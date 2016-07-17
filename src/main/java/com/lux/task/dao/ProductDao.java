package com.lux.task.dao;

import com.lux.task.dao.models.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> getAll();
    public Product getByName(String name);
    public Product getById(int id);
    public int save(final Product product);

}
