package com.wissen.esds.mobile;

import com.wissen.esds.model.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wissen.esds.service.DatabaseService;

@RestController
public class LoginRest {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/doLoginWithMobile", method = RequestMethod.POST)
    public boolean doLoginWithMobile(Personnel personnel) {
        String query = "select count(*) from personnels where username=? and password=?";
        return databaseService.checkLogin(query, Personnel.rowMapper(), personnel.getUserName(), personnel.getPassword());
    }
}
