package com.lux.task.dao.services;

import com.lux.task.dao.ProductDao;
import com.lux.task.dao.impl.ProductDaoImpl;
import com.lux.task.dao.models.Product;
import com.lux.task.dao.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductDao dao;

    public Map<Integer, String> getListForUiSelect(){
        List<Product> allProducts = dao.getAll();
        List<String> productNamesList = new ArrayList<String>();
        LinkedHashMap<Integer, String> mapForUi = new LinkedHashMap<>();
        for (Product product : allProducts) {
            mapForUi.put(product.getId(), product.getName());
        }
        return mapForUi;
    }

    public void fillProductInPurchaseByProductId(Purchase purchase){
        Product productById = dao.getById(purchase.getProduct().getId());
        purchase.setProduct(productById);
    }
}
