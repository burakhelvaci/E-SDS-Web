package com.wissen.esds.rest;

import com.wissen.esds.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.wissen.esds.service.DatabaseService;

@RestController
public class OrderDetailRest {

    @Autowired
    DatabaseService databaseService;

    @ResponseBody
    @RequestMapping(value = "orderDetail", method = RequestMethod.POST)
    public String getOrderDetail(Order order) {        
        return databaseService.fetchOrderDetailData(order);
    }
}
