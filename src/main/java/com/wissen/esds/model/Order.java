package com.wissen.esds.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class Order {

    private String id;
    private Visit visit;
    private String orderDate;
    private String totalPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PreparedStatementCreator insert() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "";
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                return preparedStatement;
            }
        };
    }

    public PreparedStatementCreator update() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "";
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                return preparedStatement;
            }
        };
    }

    public PreparedStatementCreator delete() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "delete from orders where id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getId());
                return preparedStatement;
            }
        };
    }

    public static RowMapper<Order> rowMapper() {
        return new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet rs, int i) throws SQLException {
                Order order = new Order();
                order.setId(rs.getString(1));
                order.setVisit(new Visit());
                order.getVisit().setPersonnel(new Personnel());
                order.getVisit().getPersonnel().setName(rs.getString(2));
                order.getVisit().setCustomer(new Customer());
                order.getVisit().getCustomer().setName(rs.getString(3));
                order.setOrderDate(rs.getString(4));
                order.setTotalPrice(rs.getString(5));
                return order;
            }
        };
    }
}
