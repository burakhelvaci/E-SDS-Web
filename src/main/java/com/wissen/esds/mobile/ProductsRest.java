package com.wissen.esds.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wissen.esds.service.DatabaseService;

@RestController
public class ProductsRest {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/getProductsForMobile", method = RequestMethod.POST)
    public String getProducts(@RequestParam("id") int id) {
      /*  String query;
        String productsJSON;
        
        if (id == 0) {
            query = "select * from products ";
            Object[] params = {};
            productsJSON = databaseService.fetchJson(query, params);
        } else {
            query = "select * from products where category_id=?";
            Object[] params = {id};
            productsJSON = databaseService.fetchJson(query, params);
        }
*/
        return null;
    }
}
