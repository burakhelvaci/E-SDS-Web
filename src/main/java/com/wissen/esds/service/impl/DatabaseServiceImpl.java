package com.wissen.esds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wissen.esds.dao.DatabaseDao;
import com.wissen.esds.model.Chart;
import com.wissen.esds.model.OrderDetail;
import com.wissen.esds.service.DatabaseService;
import java.util.List;
import org.json.JSONArray;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    DatabaseDao databaseDao;

    @Override
    public <T> List<T> fetch(String query, RowMapper<T> rowMapper, Object... params) {
        return databaseDao.fetch(query, rowMapper, params);
    }

    @Override
    public String fetchOrderDetailData(String query, RowMapper<OrderDetail> rowMapper, Object... params) {
        List<OrderDetail> list = databaseDao.fetch(query, rowMapper, params);
        JSONArray jSONArray = new JSONArray();
        for (OrderDetail orderDetail : list) {
            JSONArray jSONArrayElements = new JSONArray();
            jSONArrayElements.put(orderDetail.getProduct().getName());
            jSONArrayElements.put(orderDetail.getProduct().getPrice());
            jSONArrayElements.put(orderDetail.getProductCount());
            jSONArrayElements.put(orderDetail.getOrder().getOrderDate());
            jSONArray.put(jSONArrayElements);
        }
        return jSONArray.toString();
    }

    @Override
    public String fetchGoogleChartData(String query, RowMapper<Chart> rowMapper) {
        List<Chart> list = databaseDao.fetch(query, rowMapper);

        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArrayTitle = new JSONArray();
        jSONArrayTitle.put("Task");
        jSONArrayTitle.put("Hours per Day");
        jSONArray.put(jSONArrayTitle);

        for (Chart chart : list) {
            JSONArray jSONArrayElements = new JSONArray();
            jSONArrayElements.put(chart.getPersonnel().getName());
            jSONArrayElements.put(chart.getOrderCount());
            jSONArray.put(jSONArrayElements);
        }
        return jSONArray.toString();
    }

    @Override
    public void affect(String query, Object... params) {
        databaseDao.affect(query, params);
    }

    @Override
    public void affectDev(PreparedStatementCreator preparedStatementCreator) {
        databaseDao.affectDev(preparedStatementCreator);
    }

    @Override
    public <T> boolean checkLogin(String query, RowMapper<T> rowMapper, Object... params) {
        List<T> list = databaseDao.fetch(query, rowMapper, params);
        return !list.isEmpty();
    }
}
