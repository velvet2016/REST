package com.lux.task.dao.impl;

import com.lux.task.dao.ProductDao;
import com.lux.task.dao.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Product> getAll() {
        String sql = "select * from PRODUCT order by product_name";
        return jdbcTemplate.getJdbcOperations().query(sql, new ProductRowMapper());
    }

    public Product getProductByName(String name) {
        String sql = "select * from PRODUCT pr where pr.product_name = ? ";
        return jdbcTemplate.getJdbcOperations().query(sql, new ProductRowMapper(), name).get(0);
    }

    @Override
    public Product getProductById(int id) {
        String sql = "select * from PRODUCT pr where pr.product_id = ? ";
        return jdbcTemplate.getJdbcOperations().query(sql, new ProductRowMapper(), id).get(0);
    }

    public int saveProduct(final Product product) {
        final PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
                String sql = "insert into PRODUCT (product_name, product_price) values (?, ?);";
                final PreparedStatement ps = connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, product.getName());
                ps.setDouble(2, product.getPrice());
                return ps;
            }
        };
        final KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.getJdbcOperations().update(psc, holder);
        return holder.getKey().intValue();
    }

    private static class ProductRowMapper implements RowMapper<Product> {
        public Product mapRow(ResultSet rs, int i) throws SQLException {
            Product product = new Product();
            product.setId(rs.getInt("product_id"));
            product.setName(rs.getString("product_name"));
            product.setPrice(rs.getDouble("product_price"));
            return product;
        }
    }
}
