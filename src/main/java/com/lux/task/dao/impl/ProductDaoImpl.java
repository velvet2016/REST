package com.lux.task.dao.impl;

import com.lux.task.dao.ProductDao;
import com.lux.task.dao.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Product> getAll() {
        String sql = "select * from PRODUCTS order by name";
       return jdbcTemplate.getJdbcOperations().query(sql, new RowMapper<Product>() {
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                return product;
            }
        });
    }

    public Product getProductByName(String name) {
        return null;
    }

}
