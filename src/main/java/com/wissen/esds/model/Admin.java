package com.wissen.esds.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class Admin {

    private String id;
    private String name;
    private String userName;
    private String email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static RowMapper<Admin> rowMapper() {
        return new RowMapper<Admin>() {
            @Override
            public Admin mapRow(ResultSet rs, int i) throws SQLException {
                Admin admin = new Admin();
                admin.setId(rs.getString(1));
                admin.setName(rs.getString(2));
                admin.setUserName(rs.getString(3));
                admin.setEmail(rs.getString(4));
                admin.setPassword(rs.getString(5));
                return admin;
            }
        };
    }
}
