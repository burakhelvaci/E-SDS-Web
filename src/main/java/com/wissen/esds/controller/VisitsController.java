package com.wissen.esds.controller;

import com.wissen.esds.model.Customer;
import com.wissen.esds.model.Personnel;
import com.wissen.esds.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wissen.esds.service.DatabaseService;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class VisitsController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/visits", method = RequestMethod.GET)
    public String visits(Model model) {
        String query
                = "select visits.id,  personnels.`name`, customers.`name`, create_date, visit_date, check_location from visits "
                + "inner join personnels on personnel_id = personnels.id "
                + "inner join customers on customer_id = customers.id";
        model.addAttribute("visitList", databaseService.fetch(query, Visit.rowMapper()));
        query = "select * from personnels";
        model.addAttribute("personnelListForVisit", databaseService.fetch(query, Personnel.rowMapper()));
        query = "select * from customers";
        model.addAttribute("customerListForVisit", databaseService.fetch(query, Customer.rowMapper()));
        return "adminPanel";
    }

    @RequestMapping(value = "/addVisit", method = RequestMethod.POST)
    public String addVisit(Model model, Visit visit) {
        databaseService.affectDev(visit.insert());
        return "redirect:/visits";
    }

    @RequestMapping(value = "/updateVisit", method = RequestMethod.POST)
    public String updateVisit(Model model, Visit visit) {
        databaseService.affectDev(visit.update());
        return "redirect:/visits";
    }

    @RequestMapping(value = "/deleteVisit", method = RequestMethod.POST)
    public String deleteVisit(Model model, Visit visit) {
        databaseService.affectDev(visit.delete());
        return "redirect:/visits";
    }
}
