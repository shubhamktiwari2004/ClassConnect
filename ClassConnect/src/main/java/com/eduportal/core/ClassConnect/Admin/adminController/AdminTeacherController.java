package com.eduportal.core.ClassConnect.Admin.adminController;

import com.eduportal.core.ClassConnect.Admin.AdminModel.AdminTeacherModel;
import com.eduportal.core.ClassConnect.Admin.AdminRepo.AdminTeacherRepo;
import com.eduportal.core.ClassConnect.Admin.AdminService.AdminTeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminTeacherController {

    private final AdminTeacherRepo adminTeacherRepo;
    private final AdminTeacherService adminTeacherService;

    public AdminTeacherController(AdminTeacherRepo adminTeacherRepo, AdminTeacherService adminTeacherService) {
        this.adminTeacherRepo = adminTeacherRepo;
        this.adminTeacherService = adminTeacherService;
    }

    @GetMapping("admin/teacher")
    public String getAllTeachers(Model model) {

        ArrayList<AdminTeacherModel> teachers = adminTeacherService.getTeachersList();;

        // Add the data to the model
        model.addAttribute("teachers", teachers);
        System.out.println("list of teachers"+teachers);

        return "admin/teacher";
    }
    @GetMapping("admin/teacher/edit/{id}")
    @ResponseBody
    public AdminTeacherModel getTeacherById(@PathVariable("id") Long id) {
        List<AdminTeacherModel> teacherList = adminTeacherService.getTeacherById(id);

        // Check if the list is not empty and return the first teacher
        if (!teacherList.isEmpty()) {
            return teacherList.get(0);  // Return the first teacher in the list
        } else {
            return null; // Or handle as needed (e.g., throw an exception)
        }
    }

}
