package com.wissen.esds.controller;

import com.wissen.esds.model.Category;
import com.wissen.esds.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wissen.esds.service.DatabaseService;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class ProductsController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String product(Model model) {
        String query = "select * from categories";
        model.addAttribute("categoryList", databaseService.fetch(query, Category.rowMapper()));
        query = "select * from products";
        model.addAttribute("productList", databaseService.fetch(query, Product.rowMapper()));
        return "products";
    }

    @RequestMapping(value = "/products/{name}/{id}", method = RequestMethod.GET)
    public String product(Model model, Category category) {
        String query = "select * from categories";
        model.addAttribute("categoryList", databaseService.fetch(query, Category.rowMapper()));
        query = "select * from products where category_id=?";
        model.addAttribute("productList", databaseService.fetch(query, Product.rowMapper(), category.getId()));
        return "products";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(Model model, Product product) {
        databaseService.affectDev(product.insert());
        return "redirect:/products";
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public String updateProduct(Model model, Product product) {
        databaseService.affectDev(product.update());
        return "redirect:/products";
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public String deleteProduct(Model model, Product product) {
        databaseService.affectDev(product.delete());
        return "redirect:/products";
    }
}
