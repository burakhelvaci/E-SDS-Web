package com.wissen.esds.mobile;

import com.wissen.esds.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wissen.esds.service.DatabaseService;

@RestController
public class VisitsRest {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/getVisitsForMobile", method = RequestMethod.POST)
    public String getVisits(@RequestParam("username") String username) {
    /*    String query = "select customers.`name`, customers.phone_number, customers.location, "
                + "customers.address, visits.visit_date, visits.id from visits "
                + "inner join personnels on visits.personnel_id=personnels.id "
                + "inner join customers on visits.customer_id=customers.id "
                + "where personnels.username=? "
                + "ORDER BY visits.visit_date";
        Object[] params = {username};
        String visitsJSON = databaseService.fetchJson(query, params);*/
        return null;
    }

    @RequestMapping(value = "/logVisitForMobile", method = RequestMethod.POST)
    public void logVisits(Visit visit) {
        String query = "update visits set check_location='Evet' where id=?";
        databaseService.affect(query, visit.getId());
    }
}
