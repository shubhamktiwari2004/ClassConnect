package com.eduportal.core.ClassConnect.Admin.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminAppController {

    @RequestMapping("admin/home")
    public String adminHome() {
        return "admin/index";
    }

    @RequestMapping("admin/teacher")
    public String adminTeacher() {
        return "admin/teacher";
    }

}
