package com.lux.task.dao.impl;

import com.lux.task.dao.PurchaseDao;
import com.lux.task.dao.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PurchaseDaoImpl implements PurchaseDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public boolean storePurchase(Purchase purchase) {
     /*   String sql = "insert into purchases (product, quantity, purchase_date) " +
                "values (:productName, :quantity, STR_TO_DATE(:date, '%Y-%m-%d') )";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productName", purchase.getProductName());
        params.addValue("quantity", purchase.getQuantity());
        params.addValue("date", purchase.getPurchaseDate());*/

        String sql = "insert into PURCHASES (product, quantity, purchase_date) " +
                "values (?, ?, ? )";
        jdbcTemplate.getJdbcOperations().update(sql, purchase.getProductName(),purchase.getQuantity(),purchase.getPurchaseDate());
        return false;
    }
}
