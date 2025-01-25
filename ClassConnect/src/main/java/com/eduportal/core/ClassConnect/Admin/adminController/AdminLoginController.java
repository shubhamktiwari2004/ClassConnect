package com.eduportal.core.ClassConnect.Admin.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminLoginController {



    @GetMapping("admin/login")
    public String login(){
        return "admin/login";
    }


    @GetMapping("admin/home")
    public String home(){
        return "admin/index";
    }


}
