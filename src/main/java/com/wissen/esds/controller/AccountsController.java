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
        if (databaseService.checkLogin(admin)) {
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
        databaseService.update(admin);
        return "redirect:/login";
    }
}
