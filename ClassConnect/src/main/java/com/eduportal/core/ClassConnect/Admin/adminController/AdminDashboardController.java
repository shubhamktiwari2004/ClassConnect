package com.eduportal.core.ClassConnect.Admin.adminController;

import com.eduportal.core.ClassConnect.Admin.AdminModel.AdminTeacherModel;
import com.eduportal.core.ClassConnect.Admin.AdminService.AdminDashboardService;
import com.eduportal.core.ClassConnect.Admin.AdminService.AdminTeacherService;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class AdminDashboardController {

    private final AdminTeacherService adminTeacherService;
    private final AdminDashboardService adminDashboardService;

    public AdminDashboardController(AdminTeacherService adminTeacherService, AdminDashboardService adminDashboardService) {
        this.adminTeacherService = adminTeacherService;
        this.adminDashboardService = adminDashboardService;
    }

    @GetMapping("admin/home")
    public String getAllTeachers(Model model) {

        ArrayList<AdminTeacherModel> teachers = adminTeacherService.getTeachersList();;

        // Add the data to the model
        model.addAttribute("teachers", teachers);
        System.out.println("list of teachers"+teachers);

        return "admin/index";
    }
    @GetMapping("admin/home/{id}")
    @ResponseBody
    public ArrayList<StudentLoginModel> getTeacherById(@PathVariable("id") int id) {
        System.out.println("teacher_id1"+id);

        ArrayList<StudentLoginModel> studentList = adminDashboardService.getStudentByTeacherId(id);
        System.out.println("list of students are:"+studentList);
        // Check if the list is not empty and return the first teacher
        if (!studentList.isEmpty()) {
            return studentList;  // Return the first teacher in the list
        } else {
            return null; // Or handle as needed (e.g., throw an exception)
        }
    }
}
