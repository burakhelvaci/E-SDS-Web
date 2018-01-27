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
        model.addAttribute("orderList", databaseService.fetch(Order.class));
        return "adminPanel";
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    public String deleteOrder(Model model, Order order) {
        databaseService.delete(order);
        return "redirect:/orders";
    }
}
