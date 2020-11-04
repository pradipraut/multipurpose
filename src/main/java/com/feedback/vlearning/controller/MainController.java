package com.feedback.vlearning.controller;

import com.feedback.vlearning.branch.BranchConverter;
import com.feedback.vlearning.configuration.SecurityUtility;
import com.feedback.vlearning.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    SecurityUtility securityUtility;

    @Autowired
    BranchConverter branchConverter;

    @GetMapping("/")
    public String getHome() {
        return "redirect:/login";
    }

    @GetMapping("/blank")
    public String getBlank() {
        return "blank";
    }

    @GetMapping("/login")
    public String login() {
        try {
            User user = securityUtility.getSecurity();
            if (user.getUserType().name().equalsIgnoreCase("SYSTEM_ADMIN"))
                return "redirect:/auth/admin/dashboard";
            else if(user.getUserType().name().equalsIgnoreCase("ADMIN"))
                return "redirect:/auth/dashboard";
            else
                return "redirect:/404";
        } catch (Exception e) {
            return "login";
        }
    }

    @GetMapping("/404")
    public String getEmptyPage(){
        return "404";
    }

    @GetMapping("/auth/dashboard")
    public String getBranchDashboard(Model model) {
        return "branchindex";
    }


}
