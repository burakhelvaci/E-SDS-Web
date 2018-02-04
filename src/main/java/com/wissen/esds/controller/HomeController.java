package com.wissen.esds.controller;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Chart;
import com.wissen.esds.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wissen.esds.service.DatabaseService;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.ui.Model;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class HomeController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(Model model) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Chart.class);
        Root root = criteriaQuery.from(Order.class);
        criteriaQuery.multiselect(root.get("visit").get("personnel"), criteriaBuilder.count(root)).groupBy(root.get("visit").get("personnel")).orderBy(criteriaBuilder.desc(criteriaBuilder.count(root)));
        model.addAttribute("chartList", databaseService.fetchAsObject(session, criteriaQuery));
        return "adminpanel";
    }
}
