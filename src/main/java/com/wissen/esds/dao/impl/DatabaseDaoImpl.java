package com.wissen.esds.dao.impl;

import com.wissen.esds.HibernateUtility;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.wissen.esds.dao.DatabaseDao;

@Repository
public class DatabaseDaoImpl implements DatabaseDao {

    @Override
    public <T> Query<T> select(Session session, CriteriaQuery<T> criteriaQuery) {
        Query<T> query = session.createQuery(criteriaQuery);
        return query;
    }

    @Override
    public <T> void insert(T object) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
    }

    @Override
    public <T> void update(T object) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
    }

    @Override
    public <T> void delete(T object) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
    }
}
