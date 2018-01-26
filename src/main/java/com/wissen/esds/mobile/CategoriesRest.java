package com.wissen.esds.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wissen.esds.service.DatabaseService;

@RestController
public class CategoriesRest {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/getCategoriesForMobile", method = RequestMethod.POST)
    public String getCategories() {
        String query = "select id, `name` from categories";
        String categoriesJSON = "";//databaseService.fetchJson(query, null);
        return categoriesJSON;
    }

}
