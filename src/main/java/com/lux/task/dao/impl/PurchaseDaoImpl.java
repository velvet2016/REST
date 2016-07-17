package com.lux.task.dao.impl;

import com.lux.task.dao.PurchaseDao;
import com.lux.task.dao.models.Product;
import com.lux.task.dao.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseDaoImpl implements PurchaseDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    public static final String INSERT_PURCHASE = "insert into PURCHASE (product_id, quantity, purchase_date) " +
                                                                "values (?, ?, ? )";
    public static final String SQL_GET_ALL_PURCHASES = "select pc.purchase_id, " +
            "pc.product_id, " +
            "pc.quantity, " +
            "pc.purchase_date, " +
            "pr.product_name, " +
            "pr.product_price \n" +
            "from PURCHASE pc\n" +
            "join PRODUCT pr on (pc.product_id=pr.product_id)";

    public int save(final Purchase purchase) {
        final PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {

                final PreparedStatement ps = connection.prepareStatement(INSERT_PURCHASE,
                        Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, purchase.getProduct().getId());
                ps.setInt(2, purchase.getQuantity());
                ps.setDate(3, new java.sql.Date(purchase.getPurchaseDate().getTime()));
                return ps;
            }
        };
        final KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.getJdbcOperations().update(psc, holder);
        return holder.getKey().intValue();
    }

    @Override
    public Purchase getById(int id) {
        String condition = "where pc.purchase_id = ?";
        return jdbcTemplate.getJdbcOperations().query(SQL_GET_ALL_PURCHASES+condition, new PurchaseRowMapper(), id).get(0);
    }


    @Override
    public List<Purchase> saveBatch(final List<Purchase> purchases) {
        int[] ints = jdbcTemplate.getJdbcOperations().batchUpdate(INSERT_PURCHASE, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Purchase purchase = purchases.get(i);
                ps.setInt(1, purchase.getProduct().getId());
                ps.setInt(2, purchase.getQuantity());
                ps.setDate(3, new java.sql.Date(purchase.getPurchaseDate().getTime()));
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

    @Override
    public List<Purchase> getAll() {
        return jdbcTemplate.getJdbcOperations().query(SQL_GET_ALL_PURCHASES, new PurchaseRowMapper());
    }

    private static class PurchaseRowMapper implements RowMapper<Purchase> {
        @Override
        public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getDouble("product_price")
            );

            return new Purchase(
                    rs.getInt("purchase_id"),
                    product,
                    rs.getInt("quantity"),
                    new java.util.Date(rs.getDate("purchase_date").getTime())
            );
        }
    }



}
