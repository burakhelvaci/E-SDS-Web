package com.wissen.esds.rest;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Personnel;
import com.wissen.esds.model.Visit;
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
public class VisitsRest {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/api/visit/getvisits", method = RequestMethod.POST)
    public String getVisits(Personnel personnel) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Visit.class);
        Root root = criteriaQuery.from(Visit.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("personnel").get("userName"), personnel.getUserName())).orderBy(criteriaBuilder.asc(root.get("visitDate")));
        return databaseService.fetchAsJson(session, criteriaQuery, new Visit());
    }

    @RequestMapping(value = "/api/visit/logvisit", method = RequestMethod.POST)
    public void logVisits(Visit visit) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Visit.class);
        Root root = criteriaQuery.from(Visit.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), visit.getId()));
        Visit visitTemp = (Visit) databaseService.fetchAsObject(session, criteriaQuery).get(0);
        visitTemp.setCheckLocation("Evet");
        databaseService.update(visitTemp);
    }
}
