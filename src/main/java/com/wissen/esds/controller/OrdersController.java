package com.wissen.esds.controller;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import com.wissen.esds.service.DatabaseService;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class OrdersController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String orders(Model model) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root root = criteriaQuery.from(Order.class);
        criteriaQuery.select(root);
        model.addAttribute("orderList", databaseService.fetchAsObject(session, criteriaQuery));
        return "adminpanel";
    }

    @RequestMapping(value = "/order/deleteorder", method = RequestMethod.POST)
    public String deleteOrder(Model model, Order order) {
        databaseService.delete(order);
        return "redirect:/order";
    }
}
