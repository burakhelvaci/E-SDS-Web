package com.wissen.esds.rest;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Category;
import com.wissen.esds.model.Topic;
import com.wissen.esds.model.Personnel;
import com.wissen.esds.service.DatabaseService;
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

    @RequestMapping(value = "/api/messenger/sendtopic", method = RequestMethod.GET)
    public String sendTopic(Topic topic) {
        String url = "https://fcm.googleapis.com/fcm/send";
        String key = "AAAA4OYn4_0:APA91bFUs-j6WYyad6iD3CmbP1eNKuyL5fMH8I1-TAB2y3dre5e-vlZR633sjIH0y8libR_o5i6p-iFBddehDmzTaSViAe19Y2n_dFf0tWnEeOVqeODyCMcOWJ_cmmgHq6TukWBvtQKN";
        String response = "";
        try {            
            JSONObject requestBody = new JSONObject();
            requestBody.put("to", "/topics/" + topic.getTopic());
            requestBody.put("data", new JSONObject().put("message", topic.getMessage()));
            response = Jsoup.connect(url).timeout(1000).ignoreContentType(true).header("Content-Type", "application/json").header("Authorization", "key=" + key).requestBody(requestBody.toString()).post().text();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
