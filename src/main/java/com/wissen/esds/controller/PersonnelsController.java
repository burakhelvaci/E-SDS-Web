package com.wissen.esds.controller;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Personnel;
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
public class PersonnelsController {

    @Autowired
    DatabaseService databaseService;
    
    @RequestMapping(value = "/personnel", method = RequestMethod.GET)
    public String personnels(Model model) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Personnel.class);
        Root root = criteriaQuery.from(Personnel.class);
        criteriaQuery.select(root);
        model.addAttribute("personnelList", databaseService.fetchAsObject(session, criteriaQuery));
        return "/adminpanel";
    }

    @RequestMapping(value = "/personnel/addpersonnel", method = RequestMethod.POST)
    public String insertPersonnel(Personnel personnel) {
        databaseService.insert(personnel);
        return "redirect:/personnel";
    }

    @RequestMapping(value = "/personnel/updatepersonnel", method = RequestMethod.POST)
    public String updatePersonnel(Personnel personnel) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Personnel.class);
        Root root = criteriaQuery.from(Personnel.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), personnel.getId()));
        Personnel personnelTemp = (Personnel) databaseService.fetchAsObject(session, criteriaQuery).get(0);
        personnel.setToken(personnelTemp.getToken());
        databaseService.update(personnel);
        return "redirect:/personnel";
    }

    @RequestMapping(value = "/personnel/deletepersonnel", method = RequestMethod.POST)
    public String deletePersonnel(Personnel personnel) {
        databaseService.delete(personnel);
        return "redirect:/personnel";
    }
}
