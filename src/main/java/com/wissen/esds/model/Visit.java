package com.wissen.esds.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class Visit{

    private String id;
    private Personnel personnel;
    private Customer customer;
    private String createDate;
    private String visitDate;
    private String checkLocation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getCheckLocation() {
        return checkLocation;
    }

    public void setCheckLocation(String checkLocation) {
        this.checkLocation = checkLocation;
    }

    public PreparedStatementCreator insert() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "insert into visits values (0, ?, ?, now(), ?, 'Hayir')";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getPersonnel().getId());
                preparedStatement.setString(2, getCustomer().getId());
                preparedStatement.setString(3, getVisitDate());
                return preparedStatement;
            }
        };
    }

    public PreparedStatementCreator update() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "update visits set personnel_id=?, customer_id=?, visit_date=? where id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getPersonnel().getId());
                preparedStatement.setString(2, getCustomer().getId());
                preparedStatement.setString(3, getVisitDate());
                preparedStatement.setString(4, getId());
                return preparedStatement;
            }
        };
    }

    public PreparedStatementCreator delete() {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "delete from visits where id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, getId());
                return preparedStatement;
            }
        };
    }

    public static RowMapper<Visit> rowMapper() {
        return new RowMapper<Visit>() {
            @Override
            public Visit mapRow(ResultSet rs, int i) throws SQLException {
                Visit visit = new Visit();
                visit.setId(rs.getString(1));
                visit.setPersonnel(new Personnel());
                visit.getPersonnel().setName(rs.getString(2));
                visit.setCustomer(new Customer());
                visit.getCustomer().setName(rs.getString(3));
                visit.setCreateDate(rs.getString(4));
                visit.setVisitDate(rs.getString(5));
                visit.setCheckLocation(rs.getString(6));
                return visit;
            }
        };
    }
}
