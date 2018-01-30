package com.wissen.esds.mobile;

import com.wissen.esds.model.Order;
import com.wissen.esds.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRest {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/insertOrdersFromMobile", method = RequestMethod.POST)
    public void setOrder(Order order) {
        databaseService.insert(order);
    }

    @RequestMapping(value = "/updateOrdersFromMobile", method = RequestMethod.POST)
    public void updateOrder(Order order) {
        databaseService.update(order);
    }

    @RequestMapping(value = "/deleteOrdersFromMobile", method = RequestMethod.POST)
    public void deleteOrder(Order order) {
        databaseService.delete(order);
    }

}
