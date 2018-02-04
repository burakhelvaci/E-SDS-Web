package com.wissen.esds.controller;

import com.wissen.esds.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import com.wissen.esds.service.DatabaseService;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)

public class CategoryController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/category/addcategory", method = RequestMethod.POST)
    public String insertCategory(Model model, Category category) {
        databaseService.insert(category);
        return "redirect:/product";
    }

    @RequestMapping(value = "/category/updatecategory", method = RequestMethod.POST)
    public String updateCategory(Model model, Category category) {
        databaseService.update(category);
        return "redirect:/product";
    }

    @RequestMapping(value = "/category/deletecategory", method = RequestMethod.POST)
    public String deleteCategory(Model model, Category category) {
        databaseService.delete(category);
        return "redirect:/product";
    }
}
