package com.wissen.esds.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class Category {

    private String id;
    private String name;
    private String linkName;

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

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public PreparedStatementCreator insert() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "insert into categories values (0, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getName());
                preparedStatement.setString(2, getLinkName());
                return preparedStatement;
            }
        };
    }

    public PreparedStatementCreator update() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "update categories set name=?, link_name=? where id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getName());
                preparedStatement.setString(2, getLinkName());
                preparedStatement.setString(3, getId());
                return preparedStatement;
            }
        };
    }

    public PreparedStatementCreator delete() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "delete from categories where id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getId());
                return preparedStatement;
            }
        };
    }

    public static RowMapper<Category> rowMapper() {
        return new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int i) throws SQLException {
                Category category = new Category();
                category.setId(rs.getString(1));
                category.setName(rs.getString(2));
                category.setLinkName(rs.getString(3));
                return category;
            }
        };
    }
}
