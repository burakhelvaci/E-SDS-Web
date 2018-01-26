package com.wissen.esds.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class Chart {

    private Personnel personnel;
    private int orderCount;

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public static RowMapper<Chart> rowMapper() {
        return new RowMapper<Chart>() {
            @Override
            public Chart mapRow(ResultSet rs, int i) throws SQLException {
                Chart chart = new Chart();
                chart.setPersonnel(new Personnel());
                chart.getPersonnel().setName(rs.getString(1));
                chart.setOrderCount(rs.getInt(2));
                return chart;
            }
        };
    }
}
