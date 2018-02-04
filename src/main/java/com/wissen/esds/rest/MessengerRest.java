package com.wissen.esds.rest;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Category;
import com.wissen.esds.model.Topic;
import com.wissen.esds.model.Personnel;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wissen.esds.service.DatabaseService;

@RestController
public class MessengerRest {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/api/messenger/getusers", method = RequestMethod.POST)
    public String getUsers() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Personnel.class);
        Root root = criteriaQuery.from(Personnel.class);
        criteriaQuery.select(root);
        return databaseService.fetchAsJson(session, criteriaQuery, new Category());
    }

    @RequestMapping(value = "/api/messenger/sendtopic", method = RequestMethod.POST)
    public String sendTopic(Topic topic) {

        return "";
    }
}
