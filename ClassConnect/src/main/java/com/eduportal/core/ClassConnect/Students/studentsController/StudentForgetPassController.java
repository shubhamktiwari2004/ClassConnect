package com.eduportal.core.ClassConnect.Students.studentsController;

import com.eduportal.core.ClassConnect.Students.studentRepository.StudentLoginRepo;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class StudentForgetPassController {

    @Autowired
    private JavaMailSender mailSender;

    private final StudentLoginRepo studentLoginRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public StudentForgetPassController(StudentLoginRepo studentLoginRepo) {
        this.studentLoginRepo = studentLoginRepo;
    }

    @GetMapping("/students/forgetPassword")
    public String forgetPassword(){
        return "students/forgetPassword";
    }

    @PostMapping("/students/resetPassword")
    public String resetPassword(@RequestParam String email, RedirectAttributes redirectAttributes) {

        String trimEmails = email.trim();
        Optional<StudentLoginModel> optionalLoginModel = studentLoginRepo.findByEmail(trimEmails);

        if (studentLoginRepo.existsByEmail(trimEmails)) {
            StudentLoginModel studentLoginModel = optionalLoginModel.get();

            // Generate reset token and link
            String resetToken = generateResetToken();
            String resetLink = "http://class.connect.edu.in:8080/students/reset-password?token=" + resetToken;

            studentLoginModel.setTokenNo(resetToken);
            studentLoginRepo.save(studentLoginModel);

            // Send email
            sendResetEmail(trimEmails, resetLink);

            redirectAttributes.addFlashAttribute("successMessage", "Password reset link has been sent to your email.");
            return "redirect:/students/login"; // Return the page or message
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Username not found. Please check the email and try again.");
            return "redirect:/students/forgetPassword"; // Optionally redirect to a page that informs the user
        }
    }

    private String generateResetToken() {
        // This can be a UUID or any token generation logic
        return java.util.UUID.randomUUID().toString();
    }

    private void sendResetEmail(String email, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the link below:\n" + resetLink);

        // Send the email (ensure your application is set up with a mail sender bean)
        mailSender.send(message);
    }

    @GetMapping("/students/reset-password")
    public String showResetPasswordPage(@RequestParam String token, Model model) {
        Optional<StudentLoginModel> optionalLoginModel = studentLoginRepo.findByTokenNo(token);

        if (optionalLoginModel.isPresent()) {
            // Token is valid, display the reset password form
            model.addAttribute("token", token);  // Pass the token to the view
            return "students/resetPassword";
        } else {
            // Token is invalid or expired, show an error message
            model.addAttribute("errorMessage", "Invalid or expired token. Please request a new password reset link.");
            return "students/LinkExpired"; // Optionally redirect to a page that informs the user
        }
    }
    @PostMapping("/students/resetPasswordConfirm")
    public String resetPasswordConfirm(@RequestParam String token,
                                       @RequestParam String newPassword,
                                       @RequestParam String confirmPassword,
                                       Model model) {
        // Check if the passwords match
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Passwords do not match. Please try again.");
            model.addAttribute("token", token); // Retain the token for the form
            return "students/resetPassword"; // Stay on the reset password page
        }

        // Check if the token is valid
        Optional<StudentLoginModel> optionalLoginModel = studentLoginRepo.findByTokenNo(token);

        if (optionalLoginModel.isPresent()) {
            StudentLoginModel studentLoginModel = optionalLoginModel.get();

            // Update the password
            studentLoginModel.setPassword(passwordEncoder.encode(newPassword)); // Set the new password (you should hash it)
            studentLoginModel.setTokenNo(null); // Clear the token

            // Save the updated login model
            studentLoginRepo.save(studentLoginModel);

            model.addAttribute("successMessage", "Your password has been reset successfully.");
            return "students/login"; // Redirect to login page after password reset
        } else {
            model.addAttribute("errorMessage", "Invalid or expired token. Please request a new password reset link.");
            return "students/login"; // Redirect to forget password page
        }
    }

}
