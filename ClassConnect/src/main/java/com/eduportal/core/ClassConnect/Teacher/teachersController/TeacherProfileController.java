package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.TeacherModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.ProfileService;
import com.eduportal.core.ClassConnect.Teacher.teacherService.TeacherService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.UserTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TeacherProfileController {
    @Autowired
    private ProfileService profile;

    @PostMapping("/teachers/profile")
    public String profile(Model model ,HttpSession session){
        Integer teacherId = (Integer) session.getAttribute("teacher_id");

        List<ProfileModel> prof = profile.viewProfile(teacherId);
        model.addAttribute("profile" , prof.getFirst());
        return "teachers/profile";
    }

    @PostMapping("/teachers/editProfile")
    public String editProfile(Model model,HttpSession session) {
        Integer teacherId = (Integer) session.getAttribute("teacher_id");

        List<ProfileModel> prof = profile.viewProfile(teacherId);

        if (prof.isEmpty()) {
            System.out.println("Profile not found for teacherId: " + teacherId);
        } else {
            ProfileModel profile = prof.get(0);
        }

        model.addAttribute("profile", prof.isEmpty() ? new ProfileModel() : prof.get(0));
        return "teachers/profileEdit";
    }

    @PostMapping("/teachers/updateProfile")
    public String updateProfile(@RequestParam String name,
                                @RequestParam String email,
                                @RequestParam Long mobile,
                                @RequestParam String address, Model model, HttpSession session) {
        Integer teacherId = (Integer) session.getAttribute("teacher_id");

        // Logging for debugging
        System.out.println("Teacher ID from session: " + teacherId);

        try {
            // Check if the profile exists before updating
            List<ProfileModel> prof = profile.viewProfile(teacherId);

            if (prof.isEmpty()) {
                System.out.println("Profile not found for teacher ID: " + teacherId);
                model.addAttribute("error", "Profile not found.");
                return "error";
            }

            // Logging before updating
            System.out.println("Updating profile for teacher ID: " + teacherId);

            // Update the profile
            profile.updateProfile(teacherId, name, email, mobile, address);

            // Return updated profile page
            model.addAttribute("profile", prof.get(0)); // Assuming prof.get(0) is the updated profile
            return "teachers/profile";
        } catch (Exception e) {
            // Log the error stack trace for debugging
            e.printStackTrace();
            model.addAttribute("error", "Failed to update profile: " + e.getMessage());
            return "error";
        }
    }


}
