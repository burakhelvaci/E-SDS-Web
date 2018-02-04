package com.wissen.esds.controller;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Personnel;
import com.wissen.esds.model.Topic;
import com.wissen.esds.service.SenderService;
import com.wissen.esds.service.impl.FirebaseCloudMessageSenderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wissen.esds.service.DatabaseService;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class MessengerController {

    @Autowired
    DatabaseService databaseService;

    SenderService senderService;

    @RequestMapping(value = "/messenger", method = RequestMethod.POST)
    public String messenger(Model model) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Personnel.class);
        Root root = criteriaQuery.from(Personnel.class);
        criteriaQuery.select(root);
        model.addAttribute("personnelList", databaseService.fetchAsObject(session, criteriaQuery));
        return "messenger";
    }

    @RequestMapping(value = "/messenger/sendmessage", method = RequestMethod.POST)
    public String sendMessage(Topic topic) {
        senderService = new FirebaseCloudMessageSenderService();
        senderService.send(topic.getTopic(), topic.getMessage());
        return "redirect:/messenger";
    }
}
