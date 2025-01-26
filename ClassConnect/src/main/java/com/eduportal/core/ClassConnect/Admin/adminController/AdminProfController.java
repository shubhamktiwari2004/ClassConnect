package com.eduportal.core.ClassConnect.Admin.AdminController;

import com.eduportal.core.ClassConnect.Admin.AdminModel.AdminLoginModel;
import com.eduportal.core.ClassConnect.Admin.AdminRepo.AdminLoginRepo;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class AdminProfController {

    private final AdminLoginRepo adminLoginRepo;

    public AdminProfController(AdminLoginRepo adminLoginRepo) {
        this.adminLoginRepo = adminLoginRepo;
    }

    @GetMapping("/admin/profile")
    public String viewProfile(Model model , HttpSession session){

        Long adminId = (Long) session.getAttribute("adminId");

        System.out.println("hello "+adminId);

        AdminLoginModel adminLoginModel = adminLoginRepo.findByAdminId(adminId);

        System.out.println("hello "+adminLoginModel);

        model.addAttribute("adminProfile" , adminLoginModel);

        return  "admin/profile";
    }


    @GetMapping("/admin/editProfile")
    public String editProfile(Model model , HttpSession session){

        Long adminId = (Long) session.getAttribute("adminId");

        System.out.println("hello "+adminId);

        AdminLoginModel adminLoginModel = adminLoginRepo.findByAdminId(adminId);

        System.out.println("hello "+adminLoginModel);

        model.addAttribute("adminProfile" , adminLoginModel);

        return  "admin/profileEdit";
    }


    @Value("${file.upload-dir}")
    private String uploadDir;
    @PostMapping("/admin/updateProfile")
    public String updateProfile(@RequestParam String username,
                                @RequestParam String email,
                                @RequestParam(required = false) MultipartFile image, // Add image parameter
                                Model model, HttpSession session) {

        Long adminId = (Long) session.getAttribute("adminId");

        try{

            AdminLoginModel adminLoginModel = adminLoginRepo.findByAdminId(adminId);

            if(adminLoginModel == null){
                model.addAttribute("error", "Profile not found.");
                return "error";
            }

            // Handle image upload (if present)
            String imageUrl = null;

            System.out.println("images name :"+image);
            if (image != null && !image.isEmpty()) {
                String relativePath = "uploads/images/admin/" + adminId + "/";

                // Save the image to a directory (you can modify the path as needed)
                Path path = Paths.get(uploadDir+"admin/" + adminId + "/" + image.getOriginalFilename());

                // Create directories if they don't exist
                Files.createDirectories(path.getParent());

                // Save the image
                image.transferTo(path.toFile());

                // Set the image URL (assuming you store the image path in the profile)
                imageUrl = relativePath + image.getOriginalFilename();
            } else {
                // If no image is provided, retain the existing image URL (if needed)
                imageUrl = adminLoginModel.getProfileImage(); // Assuming `getImageUrl()` exists in `StudentLoginModel`
            }
            // Update the profile with the new details
            adminLoginRepo.updateProfile(adminId, username, email, imageUrl);

            // Add the updated profile to the model
            model.addAttribute("adminProfile", adminLoginModel);

            // Return the updated profile page
            return "redirect:/admin/profile";

        }catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to upload image: " + e.getMessage());
            return "error";
        }catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to update profile: " + e.getMessage());
            return "error";
        }


    }
}
