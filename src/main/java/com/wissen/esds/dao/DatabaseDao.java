package com.wissen.esds.dao;

import java.util.List;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public interface DatabaseDao {

    <T> List<T> fetch(String query, RowMapper<T> rowMapper, Object... params);

    void affect(String query, Object... params);
    
    void affectDev(PreparedStatementCreator preparedStatementCreator);
}
