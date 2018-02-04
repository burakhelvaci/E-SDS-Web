package com.wissen.esds.rest;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Order;
import com.wissen.esds.model.OrderDetail;
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
public class OrderDetailRest {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/api/orderdetail/getorderdetails", method = RequestMethod.POST)
    public String getOrderDetail(Order order) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(OrderDetail.class);
        Root root = criteriaQuery.from(OrderDetail.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("order").get("id"), order.getId()));
        return databaseService.fetchAsJson(session, criteriaQuery, order);
    }

    @RequestMapping(value = "/api/orderdetail/insertorder", method = RequestMethod.POST)
    public void insertOrderDetail(OrderDetail orderDetail) {
        databaseService.insert(orderDetail);
    }

    @RequestMapping(value = "/api/orderdetail/updateorderdetail", method = RequestMethod.POST)
    public void updateOrderDetail(OrderDetail orderDetail) {
        databaseService.update(orderDetail);
    }

    @RequestMapping(value = "/api/orderdetail/deleteorderdetail", method = RequestMethod.POST)
    public void deleteOrderDetail(OrderDetail orderDetail) {
        databaseService.delete(orderDetail);
    }
}
