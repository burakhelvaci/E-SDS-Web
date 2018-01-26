package com.wissen.esds.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class Customer {

    private String id;
    private String name;
    private String phoneNumber;
    private String location;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PreparedStatementCreator insert() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "insert into customers values (0, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getName());
                preparedStatement.setString(2, getPhoneNumber());
                preparedStatement.setString(3, getLocation());
                preparedStatement.setString(4, getAddress());
                return preparedStatement;
            }
        };
    }

    public PreparedStatementCreator update() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "update customers set name=?, phone_number=?, location=?, address=? where id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getName());
                preparedStatement.setString(2, getPhoneNumber());
                preparedStatement.setString(3, getLocation());
                preparedStatement.setString(4, getAddress());
                preparedStatement.setString(5, getId());
                return preparedStatement;
            }
        };
    }

    public PreparedStatementCreator delete() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "delete from customers where id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getId());
                return preparedStatement;
            }
        };
    }

    public static RowMapper<Customer> rowMapper() {
        return new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int i) throws SQLException {
                Customer customer = new Customer();
                customer.setId(rs.getString(1));
                customer.setName(rs.getString(2));
                customer.setPhoneNumber(rs.getString(3));
                customer.setLocation(rs.getString(4));
                customer.setAddress(rs.getString(5));
                return customer;
            }
        };
    }
}
