package com.lux.task.dao.impl;

import com.lux.task.dao.ReportDao;
import com.lux.task.dao.models.Product;
import com.lux.task.dao.models.ReportLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ReportDaoImpl implements ReportDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<ReportLine> getReport(int numberOfMonths) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("monthCount", numberOfMonths);
        String sql = "select pr.*,  ps.*,  pr.product_price*ps.quantity sum from PRODUCT pr\n" +
                "join PURCHASE ps on pr.product_id = ps.product_id\n" +
                "where ps.purchase_date>date_sub(now(), interval ? month) \n" +
                "and ps.purchase_date<now() order by purchase_date desc, purchase_id desc";
        return jdbcTemplate.getJdbcOperations().query(sql, new ReportLineRowMapper(), numberOfMonths);
    }
    private static class ReportLineRowMapper implements RowMapper<ReportLine> {
        @Override
        public ReportLine mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getDouble("product_price")
            );
            return new ReportLine(
                    rs.getInt("purchase_id"),
                    product,
                    rs.getInt("quantity"),
                    new java.util.Date(rs.getTimestamp("purchase_date").getTime()),
                    rs.getDouble("sum")
            );
        }
    }
}
