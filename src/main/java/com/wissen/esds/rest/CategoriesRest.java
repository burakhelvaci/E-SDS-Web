package com.wissen.esds.rest;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import com.wissen.esds.service.DatabaseService;

@RestController
public class CategoriesRest {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/api/category/getcategories", method = RequestMethod.POST)
    public String getCategories() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root root = criteriaQuery.from(Category.class);
        criteriaQuery.select(root);
        return databaseService.fetchAsJson(session, criteriaQuery, new Category());
    }

}
