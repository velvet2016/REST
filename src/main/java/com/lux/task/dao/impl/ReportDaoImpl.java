package com.lux.task.dao.impl;

import com.lux.task.dao.ReportDao;
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
        String sql = "select pr.*,  ps.*,  pr.price*ps.quantity sum from PRODUCTS pr\n" +
                "join PURCHASES ps on pr.name = ps.product\n" +
                "where ps.purchase_date>date_sub(now(), interval ? month) " +
                "and ps.purchase_date<now() order by purchase_date desc;";
        return jdbcTemplate.getJdbcOperations().query(sql, new RowMapper<ReportLine>() {
            public ReportLine mapRow(ResultSet resultSet, int i) throws SQLException {
                ReportLine reportLine = new ReportLine();
                reportLine.setProductName(resultSet.getString("product"));
                reportLine.setPurchaseDate(resultSet.getDate("purchase_date"));
                reportLine.setQuantity(resultSet.getInt("quantity"));
                reportLine.setSum(resultSet.getDouble("sum"));
                return reportLine;
            }
        }, numberOfMonths);
    }
}
