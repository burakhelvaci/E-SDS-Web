package com.wissen.esds.controller;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Customer;
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
public class CustomersController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String customers(Model model) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root root = criteriaQuery.from(Customer.class);
        criteriaQuery.select(root);
        model.addAttribute("customerList", databaseService.fetchAsObject(session, criteriaQuery));
        return "adminpanel";
    }

    @RequestMapping(value = "/customer/addcustomer", method = RequestMethod.POST)
    public String insertCustomer(Model model, Customer customer) {
        databaseService.insert(customer);
        return "redirect:/customer";
    }

    @RequestMapping(value = "/customer/updatecustomer", method = RequestMethod.POST)
    public String updateCustomer(Model model, Customer customer) {
        databaseService.update(customer);
        return "redirect:/customer";
    }

    @RequestMapping(value = "/customer/deletecustomer", method = RequestMethod.POST)
    public String deleteCustomer(Model model, Customer customer) {
        databaseService.delete(customer);
        return "redirect:/customer";
    }
}
