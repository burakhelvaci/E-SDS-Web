package com.wissen.esds.controller;

import com.wissen.esds.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wissen.esds.service.DatabaseService;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class OrdersController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public String orders(Model model) {
        String query
                = "SELECT orders.id, personnels.`name`, customers.`name`, orders.order_date, orders.total_price from orders "
                + "inner join visits on orders.visit_id=visits.id "
                + "inner join personnels on visits.personnel_id=personnels.id "
                + "inner join customers on visits.customer_id=customers.id";
        model.addAttribute("orderList", databaseService.fetch(query, Order.rowMapper()));
        return "adminPanel";
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    public String deleteOrder(Model model, Order order) {
        databaseService.affectDev(order.delete());
        return "redirect:/orders";
    }
}
