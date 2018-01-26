package com.wissen.esds.controller;

import com.wissen.esds.model.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wissen.esds.service.DatabaseService;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class PersonnelsController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/personnels", method = RequestMethod.GET)
    public String personnels(Model model) {
        String query = "select * from personnels";
        model.addAttribute("personnelList", databaseService.fetch(query, Personnel.rowMapper()));
        return "adminPanel";
    }

    @RequestMapping(value = "/addPersonnel", method = RequestMethod.POST)
    public String addPersonnel(Personnel personnel) {
        databaseService.affectDev(personnel.insert());
        return "redirect:/personnels";
    }

    @RequestMapping(value = "/updatePersonnel", method = RequestMethod.POST)
    public String updatePersonnel(Personnel personnel) {
        databaseService.affectDev(personnel.update());
        return "redirect:/personnels";
    }

    @RequestMapping(value = "/deletePersonnel", method = RequestMethod.POST)
    public String deletePersonnel(Personnel personnel) {
        databaseService.affectDev(personnel.delete());
        return "redirect:/personnels";
    }
}
