package com.wissen.esds.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class OrderDetail {

    private String id;
    private Order order;
    private Product product;
    private String productCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
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
                String query = "delete from orderdetail where id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getId());
                return preparedStatement;
            }
        };
    }

    public static RowMapper<OrderDetail> rowMapper() {
        return new RowMapper<OrderDetail>() {
            @Override
            public OrderDetail mapRow(ResultSet rs, int i) throws SQLException {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(new Product());
                orderDetail.getProduct().setName(rs.getString(1));
                orderDetail.getProduct().setPrice(rs.getString(2));
                orderDetail.setProductCount(rs.getString(3));
                orderDetail.setOrder(new Order());
                orderDetail.getOrder().setOrderDate(rs.getString(4));
                return orderDetail;
            }
        };
    }

}
