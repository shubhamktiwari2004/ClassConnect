package com.eduportal.core.ClassConnect.Students.studentsController;

import com.eduportal.core.ClassConnect.Students.studentService.StudentProfileService;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
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
import java.util.List;

@Controller
public class StudentProfileController {


    private final StudentProfileService profileService;

    public StudentProfileController(StudentProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/students/profile")
    public String viewProfile(Model model , HttpSession session){

        Long studentId = (Long) session.getAttribute("studentId");

        StudentLoginModel studentProfData = profileService.viewProfile(studentId);

        model.addAttribute("StudentProfile" , studentProfData);

        return  "students/profile";
    }

    @GetMapping("/students/editProfile")
    public String editProfile(Model model , HttpSession session){

        Long studentId = (Long) session.getAttribute("studentId");

        StudentLoginModel studentProfData = profileService.viewProfile(studentId);

        model.addAttribute("StudentProfile" , studentProfData);

        return  "students/profileEdit";
    }


    @Value("${file.upload-dir}")
    private String uploadDir;
    @PostMapping("/students/updateProfile")
    public String updateProfile(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam String email,
                                @RequestParam String address,
                                @RequestParam(required = false) MultipartFile image, // Add image parameter
                                Model model, HttpSession session) {

        Long studentId = (Long) session.getAttribute("studentId");

        try{

            StudentLoginModel studentProfData = profileService.viewProfile(studentId);

            if(studentProfData == null){
                model.addAttribute("error", "Profile not found.");
                return "error";
            }

            // Handle image upload (if present)
            String imageUrl = null;

            System.out.println("images name :"+image);
            if (image != null && !image.isEmpty()) {
                String relativePath = "uploads/images/students/" + studentId + "/";

                // Save the image to a directory (you can modify the path as needed)
                Path path = Paths.get(uploadDir+"students/" + studentId + "/" + image.getOriginalFilename());

                // Create directories if they don't exist
                Files.createDirectories(path.getParent());

                // Save the image
                image.transferTo(path.toFile());

                // Set the image URL (assuming you store the image path in the profile)
                imageUrl = relativePath + image.getOriginalFilename();
            } else {
                // If no image is provided, retain the existing image URL (if needed)
                imageUrl = studentProfData.getImage_url(); // Assuming `getImageUrl()` exists in `StudentLoginModel`
            }
            // Update the profile with the new details
            profileService.updateProfile(studentId, firstName,lastName, email,address, imageUrl);

            // Add the updated profile to the model
            model.addAttribute("StudentProfile", studentProfData); // Assuming prof.get(0) is the updated profile

            // Return the updated profile page
            return "redirect:/students/profile";

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
