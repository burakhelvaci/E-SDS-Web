package com.wissen.esds.service;

import com.wissen.esds.model.Chart;
import com.wissen.esds.model.OrderDetail;
import java.util.List;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public interface DatabaseService {

    <T> List<T> fetch(String query, RowMapper<T> rowMapper, Object... params);

    String fetchOrderDetailData(String query, RowMapper<OrderDetail> rowMapper, Object... params);

    String fetchGoogleChartData(String query, RowMapper<Chart> rowMapper);

    void affect(String query, Object... params);

    void affectDev(PreparedStatementCreator preparedStatementCreator);

    <T> boolean checkLogin(String query, RowMapper<T> rowMapper, Object... params);
}
