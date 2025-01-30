package com.eduportal.core.ClassConnect.Admin.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminAppController {


    @GetMapping("admin/login")
    public String login(){
        return "admin/login";
    }

}
