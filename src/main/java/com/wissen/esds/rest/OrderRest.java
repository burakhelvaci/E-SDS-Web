package com.wissen.esds.rest;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Order;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wissen.esds.service.DatabaseService;

@RestController
public class OrderRest {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/api/order/getorders", method = RequestMethod.POST)
    public String getOrder(Order order) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root root = criteriaQuery.from(Order.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("visit").get("id"), order.getVisit().getId())).orderBy(criteriaBuilder.desc(root.get("visit").get("id")));
        return databaseService.fetchAsJson(session, criteriaQuery, order);
    }

    @RequestMapping(value = "/api/order/insertorder", method = RequestMethod.POST)
    public void insertOrder(Order order) {
        databaseService.insert(order);
    }

    @RequestMapping(value = "/api/order/updateorder", method = RequestMethod.POST)
    public void updateOrder(Order order) {
        databaseService.update(order);
    }

    @RequestMapping(value = "/api/order/deleteorder", method = RequestMethod.POST)
    public void deleteOrder(Order order) {
        databaseService.delete(order);
    }

}
