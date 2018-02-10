package com.wissen.esds.rest;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Category;
import com.wissen.esds.model.Message;
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
import com.wissen.esds.service.impl.FirebaseCloudMessageSenderService;

@RestController
public class MessengerRest {

    @Autowired
    DatabaseService databaseService;
    
    FirebaseCloudMessageSenderService firebaseCloudMessageSenderService;

    @RequestMapping(value = "/api/messenger/getusers", method = RequestMethod.POST)
    public String getUsers() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Personnel.class);
        Root root = criteriaQuery.from(Personnel.class);
        criteriaQuery.select(root);
        return databaseService.fetchAsJson(session, criteriaQuery, new Category());
    }

    @RequestMapping(value = "/api/messenger/sendmessage", method = RequestMethod.POST)
    public void sendTopic(Message message) {
        firebaseCloudMessageSenderService = new FirebaseCloudMessageSenderService();
        firebaseCloudMessageSenderService.send(message.getReceiver(), message.getMessage());
    }
}
