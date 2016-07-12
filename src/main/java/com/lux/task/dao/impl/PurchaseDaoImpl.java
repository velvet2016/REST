package com.lux.task.dao.impl;

import com.lux.task.dao.PurchaseDao;
import com.lux.task.dao.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseDaoImpl implements PurchaseDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    public static final String INSERT_PURCHASE = "insert into PURCHASES (product, quantity, purchase_date) " +
                                                                "values (?, ?, ? )";

    public boolean storePurchase(Purchase purchase) {
     /*   String sql = "insert into purchases (product, quantity, purchase_date) " +
                "values (:productName, :quantity, STR_TO_DATE(:date, '%Y-%m-%d') )";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productName", purchase.getProductName());
        params.addValue("quantity", purchase.getQuantity());
        params.addValue("date", purchase.getPurchaseDate());*/

        int rowUpdated = jdbcTemplate.getJdbcOperations().update(INSERT_PURCHASE, purchase.getProductName(), purchase.getQuantity(), purchase.getPurchaseDate());
        return (rowUpdated == 1);
    }

    @Override
    public List<Purchase> storePurchases(final List<Purchase> purchases) {
        int[] ints = jdbcTemplate.getJdbcOperations().batchUpdate(INSERT_PURCHASE, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Purchase purchase = purchases.get(i);
                ps.setString(1, purchase.getProductName());
                ps.setInt(2, purchase.getQuantity());
                ps.setDate(3, new Date(purchase.getPurchaseDate().getTime()));
            }


            @Override
            public int getBatchSize() {
                return purchases.size();
            }
        });
        ArrayList<Purchase> notStored = new ArrayList<>();
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == 0){
                notStored.add(purchases.get(i));
            }
        }
        return notStored;
    }


}
