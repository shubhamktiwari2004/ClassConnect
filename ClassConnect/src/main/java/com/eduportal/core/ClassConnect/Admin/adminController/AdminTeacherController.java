package com.eduportal.core.ClassConnect.Admin.adminController;

import com.eduportal.core.ClassConnect.Admin.AdminModel.AdminTeacherModel;
import com.eduportal.core.ClassConnect.Admin.AdminRepo.AdminTeacherRepo;
import com.eduportal.core.ClassConnect.Admin.AdminService.AdminTeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class AdminTeacherController {

    private final AdminTeacherRepo adminTeacherRepo;
    private final AdminTeacherService adminTeacherService;

    public AdminTeacherController(AdminTeacherRepo adminTeacherRepo, AdminTeacherService adminTeacherService) {
        this.adminTeacherRepo = adminTeacherRepo;
        this.adminTeacherService = adminTeacherService;
    }

    @GetMapping("admin/teacher")
    public String getAllTeachers(org.springframework.ui.Model model) {

        ArrayList<AdminTeacherModel> teachers = adminTeacherService.getTeachersList();;

        // Add the data to the model
        model.addAttribute("teachers", teachers);
        System.out.println("list of teachers"+teachers);

        return "admin/teacher";
    }


}
