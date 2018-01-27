package com.wissen.esds.dao;
;

import org.hibernate.query.Query;

public interface DatabaseDao {

    <T> Query<T> fetch(Class<T> element);

    <T> void insert(T object);

    <T> void update(T object);

    <T> void delete(T object);
}
