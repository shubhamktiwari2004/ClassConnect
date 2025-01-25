package com.eduportal.core.ClassConnect.Admin.AdminController;

import com.eduportal.core.ClassConnect.Admin.AdminService.AddTeacherService;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class AddTeacherController {

    private final AddTeacherService addTeacherService;

    public AddTeacherController(AddTeacherService addTeacherService) {
        this.addTeacherService = addTeacherService;
    }


    @PostMapping("/admin/addTeacher")
    public String addTeacher(
            HttpSession session,
            @RequestParam String teachername,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String phoneno,
            @RequestParam String address,
            @RequestParam (required = false) MultipartFile teacherImage,
            Model model) {
        try {
            System.out.println("in teacher add"+ teacherImage);
            long phoneNo = Long.parseLong(phoneno);
            addTeacherService.addTeacher(teachername, username, email,phoneNo , address , teacherImage);
            return "redirect:/admin/teacher"; // Redirect to login page after successful signup
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());  // Add the error message to the model
            return "redirect:/admin/teacher";
        }
    }


}
