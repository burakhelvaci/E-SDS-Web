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
        model.addAttribute("categoryList", databaseService.fetch(Category.class));
        model.addAttribute("productList", databaseService.fetch(Product.class));
        return "products";
    }

    @RequestMapping(value = "/products/{name}/{id}", method = RequestMethod.GET)
    public String product(Model model, Category category) {
        model.addAttribute("categoryList", databaseService.fetch(Category.class));
        model.addAttribute("productList", databaseService.fetch(Product.class));
        return "products";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(Model model, Product product) {
        databaseService.insert(product);
        return "redirect:/products";
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public String updateProduct(Model model, Product product) {
        databaseService.update(product);
        return "redirect:/products";
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public String deleteProduct(Model model, Product product) {
        databaseService.delete(product);
        return "redirect:/products";
    }
}
