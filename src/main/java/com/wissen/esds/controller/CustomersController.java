package com.wissen.esds.controller;

import com.wissen.esds.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wissen.esds.service.DatabaseService;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class CustomersController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String customers(Model model) {
        model.addAttribute("customerList", databaseService.fetch(Customer.class));
        return "adminPanel";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String addCustomer(Model model, Customer customer) {
        databaseService.insert(customer);
        return "redirect:/customers";
    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
    public String updateCustomer(Model model, Customer customer) {
        databaseService.update(customer);
        return "redirect:/customers";
    }

    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
    public String deleteCustomer(Model model, Customer customer) {
        databaseService.delete(customer);
        return "redirect:/customers";
    }
}
