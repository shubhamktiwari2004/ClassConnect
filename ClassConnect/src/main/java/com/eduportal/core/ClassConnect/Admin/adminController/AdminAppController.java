package com.eduportal.core.ClassConnect.Admin.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminAppController {


    @GetMapping("admin/login")
    public String login(){
        return "admin/login";
    }


    @GetMapping("admin/home")
    public String home(){
        return "admin/index";
    }

    @GetMapping("admin/teacher")
    public String teacher(){
        return "admin/teacher";
    }

}
