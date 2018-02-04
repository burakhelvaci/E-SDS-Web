package com.wissen.esds.rest;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Category;
import com.wissen.esds.model.Product;
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
public class ProductsRest {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/api/product/getproducts", method = RequestMethod.POST)
    public String getProducts() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);
        return databaseService.fetchAsJson(session, criteriaQuery, new Product());
    }

    @RequestMapping(value = "/api/product/getproductsbycategory", method = RequestMethod.POST)
    public String getProducts(Category category) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("category").get("id"), category.getId()));
        return databaseService.fetchAsJson(session, criteriaQuery, new Product());
    }
}