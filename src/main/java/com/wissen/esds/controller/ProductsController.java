package com.wissen.esds.controller;

import com.wissen.esds.HibernateUtility;
import com.wissen.esds.model.Category;
import com.wissen.esds.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import com.wissen.esds.service.DatabaseService;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class ProductsController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String product(Model model) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root root = criteriaQuery.from(Category.class);
        criteriaQuery.select(root);
        model.addAttribute("categoryList", databaseService.fetchAsObject(session, criteriaQuery));
        criteriaQuery = criteriaBuilder.createQuery(Product.class);
        root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);
        model.addAttribute("productList", databaseService.fetchAsObject(session, criteriaQuery));
        return "product";
    }

    @RequestMapping(value = "/product/{name}/{id}", method = RequestMethod.GET)
    public String product(Model model, Category category) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root root = criteriaQuery.from(Category.class);
        criteriaQuery.select(root);
        model.addAttribute("categoryList", databaseService.fetchAsObject(session, criteriaQuery));
        criteriaQuery = criteriaBuilder.createQuery(Product.class);
        root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("category").get("id"), category.getId()));
        model.addAttribute("productList", databaseService.fetchAsObject(session, criteriaQuery));
        return "product";
    }

    @RequestMapping(value = "/product/addproduct", method = RequestMethod.POST)
    public String insertProduct(Model model, Product product) {
        databaseService.insert(product);
        return "redirect:/product";
    }

    @RequestMapping(value = "/product/updateproduct", method = RequestMethod.POST)
    public String updateProduct(Model model, Product product) {
        databaseService.update(product);
        return "redirect:/product";
    }

    @RequestMapping(value = "/product/deleteproduct", method = RequestMethod.POST)
    public String deleteProduct(Model model, Product product) {
        databaseService.delete(product);
        return "redirect:/product";
    }
}
