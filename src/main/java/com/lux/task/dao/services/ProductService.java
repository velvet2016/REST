package com.lux.task.dao.services;

import com.lux.task.dao.ProductDao;
import com.lux.task.dao.models.Product;
import com.lux.task.dao.models.Purchase;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductDao dao;

    public Map<Integer, String> getListForUiSelect(){
        List<Product> allProducts = dao.getAll();
        LinkedHashMap<Integer, String> mapForUi = new LinkedHashMap<>();
        for (Product product : allProducts) {
            mapForUi.put(product.getId(), product.getName());
        }
        return mapForUi;
    }

    public List<Product> getAll() {
        return dao.getAll();
    }

    public int add(Product product) {
        return dao.save(product);
    }

    public void fillProductInPurchaseByProductId(Purchase purchase){
        Product productById = dao.getById(purchase.getProduct().getId());
        purchase.setProduct(productById);
    }
}
