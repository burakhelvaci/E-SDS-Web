package com.wissen.esds.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class Personnel{

    private String id;
    private String name;
    private String userName;
    private String email;
    private String phoneNumber;
    private String password;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PreparedStatementCreator insert() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "insert into personnels values (0, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getName());
                preparedStatement.setString(2, getUserName());
                preparedStatement.setString(3, getEmail());
                preparedStatement.setString(4, getPhoneNumber());
                preparedStatement.setString(5, getPassword());
                return preparedStatement;
            }
        };
    }

    public PreparedStatementCreator update() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "update personnels set name=?, username=?, email=?, phone_number=?, password=? where id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getName());
                preparedStatement.setString(2, getUserName());
                preparedStatement.setString(3, getEmail());
                preparedStatement.setString(4, getPhoneNumber());
                preparedStatement.setString(5, getPassword());
                preparedStatement.setString(6, getId());
                return preparedStatement;
            }
        };
    }

    public PreparedStatementCreator delete() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "delete from personnels where id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getId());
                return preparedStatement;
            }
        };
    }

    public static RowMapper<Personnel> rowMapper() {
        return new RowMapper<Personnel>() {
            @Override
            public Personnel mapRow(ResultSet rs, int i) throws SQLException {
                Personnel personnel = new Personnel();
                personnel.setId(rs.getString(1));
                personnel.setName(rs.getString(2));
                personnel.setUserName(rs.getString(3));
                personnel.setEmail(rs.getString(4));
                personnel.setPhoneNumber(rs.getString(5));
                personnel.setPhoneNumber(rs.getString(6));
                return personnel;
            }
        };
    }
}
