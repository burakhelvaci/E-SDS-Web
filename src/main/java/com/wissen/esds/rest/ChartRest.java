package com.wissen.esds.rest;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Chart;
import com.wissen.esds.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import com.wissen.esds.service.DatabaseService;

@RestController
public class ChartRest {

    @Autowired
    DatabaseService databaseService;

    @ResponseBody
    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    public String getChart() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Chart.class);
        Root root = criteriaQuery.from(Order.class);
        criteriaQuery.multiselect(root.get("visit").get("personnel"), criteriaBuilder.count(root)).groupBy(root.get("visit").get("personnel")).orderBy(criteriaBuilder.desc(criteriaBuilder.count(root)));
        return databaseService.fetchAsJson(session, criteriaQuery, new Chart());
    }
}
