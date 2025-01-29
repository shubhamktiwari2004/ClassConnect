package com.eduportal.core.ClassConnect.Admin.adminController;

import com.eduportal.core.ClassConnect.Admin.AdminService.AddTeacherService;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            @RequestParam Long teacher_id,
            Model model) {
        try {
            System.out.println("in teacher add"+ teacherImage);
            long phoneNo = Long.parseLong(phoneno);
            if (teacher_id != null) {
                addTeacherService.updateTeacher(teacher_id,teachername, username, email,phoneNo , address , teacherImage);
            }
            else{
                addTeacherService.addTeacher(teachername, username, email,phoneNo , address , teacherImage);
            }
            return "redirect:/admin/teacher"; // Redirect to login page after successful signup
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());  // Add the error message to the model
            return "redirect:/admin/teacher";
        }
    }
    @GetMapping("/admin/deleteTeacher/{teacher_id}")
    public String deleteTeacherGet(@PathVariable Long teacher_id, Model model) {
        try {
            // Call service to delete the teacher by ID
            addTeacherService.deleteTeacher(teacher_id);

            // Redirect to the teacher list page after successful deletion
            return "redirect:/admin/teacher";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/teacher";
        }
    }






}
