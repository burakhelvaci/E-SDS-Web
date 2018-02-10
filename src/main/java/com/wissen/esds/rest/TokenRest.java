package com.wissen.esds.rest;

import com.wissen.esds.model.Personnel;
import com.wissen.esds.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenRest {
    
    @Autowired
    DatabaseService databaseService;
    
    @RequestMapping(value = "/api/token/settoken", method = RequestMethod.POST)
    public void setToken(Personnel personnel){
        databaseService.update(personnel);
    }
}
