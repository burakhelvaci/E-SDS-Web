package com.wissen.esds.dao.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.wissen.esds.dao.DatabaseDao;
import java.util.List;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class DatabaseDaoImpl extends JdbcDaoSupport implements DatabaseDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public <T> List<T> fetch(String query, RowMapper<T> rowMapper, Object... params) {
        List<T> list = getJdbcTemplate().query(query, rowMapper ,params);
        return list;
    }

    @Override
    public void affect(String query, Object... params) {
        getJdbcTemplate().update(query, params);
    }
    
    @Override
    public void affectDev(PreparedStatementCreator preparedStatementCreator) {
        getJdbcTemplate().update(preparedStatementCreator);
    }
}
