package com.wissen.esds.controller;

import com.wissen.esds.model.Admin;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wissen.esds.service.DatabaseService;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class AccountsController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String mainPage() {
        return "login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(HttpSession session, Admin admin) {
        String query = "select * from admins where username=? and password=?";
        if (databaseService.checkLogin(query, Admin.rowMapper(), admin.getUserName(), admin.getPassword())) {
            session.setAttribute("admin", admin.getUserName());
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/doLogout", method = RequestMethod.GET)
    public String doLogout(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/login";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(HttpSession session, Admin admin) {
        admin.setUserName(session.getAttribute("admin").toString());
        String query = "update admins set password=? where username=?";
        databaseService.affect(query, admin.getPassword(), admin.getUserName());
        return "redirect:/login";
    }
}
