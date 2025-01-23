package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.TeacherModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.ProfileService;
import com.eduportal.core.ClassConnect.Teacher.teacherService.TeacherService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.UserTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class TeacherProfileController {
    @Autowired
    private ProfileService profile;

    @GetMapping("/teachers/profile")
    public String profile(Model model ,HttpSession session){
        Integer teacherId = (Integer) session.getAttribute("teacher_id");

        List<ProfileModel> prof = profile.viewProfile(teacherId);
        model.addAttribute("profile" , prof.getFirst());
        return "teachers/profile";
    }

    @GetMapping("/teachers/editProfile")
    public String editProfile(Model model,HttpSession session) {
        Integer teacherId = (Integer) session.getAttribute("teacher_id");

        List<ProfileModel> prof = profile.viewProfile(teacherId);

        if (prof.isEmpty()) {
            System.out.println("Profile not found for teacherId: " + teacherId);
        } else {
            ProfileModel profile = prof.get(0);
            System.out.println("Profile not found for profile: " + profile);
        }

        model.addAttribute("profile", prof.isEmpty() ? new ProfileModel() : prof.get(0));
        return "teachers/profileEdit";
    }




    @Value("${file.upload-dir}")
    private String uploadDir;
    @PostMapping("/teachers/updateProfile")
    public String updateProfile(@RequestParam String name,
                                @RequestParam String email,
                                @RequestParam Long mobile,
                                @RequestParam String address,
                                @RequestParam(required = false) MultipartFile image, // Add image parameter
                                Model model, HttpSession session) {

        Integer teacherId = (Integer) session.getAttribute("teacher_id");

        try {
            // Check if the profile exists before updating
            List<ProfileModel> prof = profile.viewProfile(teacherId);

            System.out.println("hello i am here :"+ prof);
            if (prof.isEmpty()) {
                model.addAttribute("error", "Profile not found.");
                return "error";
            }

            // Handle image upload (if present)
            String imageUrl = null;

            System.out.println("images name :"+image);
            if (image != null && !image.isEmpty()) {
                String relativePath = "uploads/images/" + teacherId + "/";

                // Save the image to a directory (you can modify the path as needed)
                Path path = Paths.get(uploadDir + teacherId + "/" + image.getOriginalFilename());

                System.out.println("Creating directory: " + path.getParent());

                // Create directories if they don't exist
                Files.createDirectories(path.getParent());

                // Save the image
                image.transferTo(path.toFile());

                // Set the image URL (assuming you store the image path in the profile)
                imageUrl = relativePath + image.getOriginalFilename();
            }else {
                // If no image is provided, retain the existing image URL (if needed)
                imageUrl = prof.get(0).getProfile_image(); // Assuming `getImageUrl()` exists in `StudentLoginModel`
            }

            // Update the profile with the new details
            profile.updateProfile(teacherId, name, email, mobile, address, imageUrl);

            // Add the updated profile to the model
            model.addAttribute("profile", prof.get(0)); // Assuming prof.get(0) is the updated profile

            // Return the updated profile page
            return "redirect:/teachers/profile";

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to upload image: " + e.getMessage());
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to update profile: " + e.getMessage());
            return "error";
        }
    }


}
