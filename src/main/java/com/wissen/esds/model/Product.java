package com.wissen.esds.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class Product{

    private String id;
    private Category category;
    private String name;
    private String price;
    private String imgLink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public PreparedStatementCreator insert() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "insert into products values (0, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getCategory().getId());
                preparedStatement.setString(2, getName());
                preparedStatement.setString(3, getPrice());
                preparedStatement.setString(4, getImgLink());
                return preparedStatement;
            }
        };
    }

    public PreparedStatementCreator update() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "update products set category_id=?, name=?, price=?, img_link=? where id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getCategory().getId());
                preparedStatement.setString(2, getName());
                preparedStatement.setString(3, getPrice());
                preparedStatement.setString(4, getImgLink());
                preparedStatement.setString(5, getId());
                return preparedStatement;
            }
        };
    }

    public PreparedStatementCreator delete() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "delete from products where id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getId());
                return preparedStatement;
            }
        };
    }

    public static RowMapper<Product> rowMapper() {
        return new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int i) throws SQLException {
                Product product = new Product();
                product.setId(rs.getString(1));
                product.setCategory(new Category());
                product.getCategory().setId(rs.getString(2));
                product.setName(rs.getString(3));
                product.setPrice(rs.getString(4));
                product.setImgLink(rs.getString(5));
                return product;
            }
        };
    }
}
