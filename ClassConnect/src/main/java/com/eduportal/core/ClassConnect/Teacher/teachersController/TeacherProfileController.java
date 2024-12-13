package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.TeacherModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.ProfileService;
import com.eduportal.core.ClassConnect.Teacher.teacherService.TeacherService;
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
    public String profile(@RequestParam int teacherId , Model model){
        List<ProfileModel> prof = profile.viewProfile(teacherId);
        model.addAttribute("profile" , prof.getFirst());
        return "teachers/profile";
    }

    @PostMapping("/teachers/editProfile")
    public String editProfile(@RequestParam int teacherId, Model model) {
        List<ProfileModel> prof = profile.viewProfile(teacherId);

        if (prof.isEmpty()) {
            System.out.println("Profile not found for teacherId: " + teacherId);
        } else {
            ProfileModel profile = prof.get(0);
            System.out.println("Profile found: " + profile);
            System.out.println("Name: " + profile.getName());
            System.out.println("Email: " + profile.getEmail());
            System.out.println("Mobile: " + profile.getMobile());
            System.out.println("Address: " + profile.getAddress());
        }

        model.addAttribute("profile", prof.isEmpty() ? new ProfileModel() : prof.get(0));
        return "teachers/profileEdit";
    }

    @PostMapping("/teachers/updateProfile")
    public String updateProfile(@RequestParam int teacherId,
                                @RequestParam String name,
                                @RequestParam String email,
                                @RequestParam Long mobile,
                                @RequestParam String address, Model model) {
        try {
            // Check if the profile exists before updating
            List<ProfileModel> prof = profile.viewProfile(teacherId);
            if (prof.isEmpty()) {
                model.addAttribute("error", "Profile not found for update.");
                return "teachers/errorPage";
            }

            // Update the profile using the custom query
            profile.updateProfile(teacherId, name, email, mobile, address);

            model.addAttribute("profile" , prof.getFirst());
            return "teachers/profile";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update profile: " + e.getMessage());
            return "teachers/errorPage";
        }
    }

}
