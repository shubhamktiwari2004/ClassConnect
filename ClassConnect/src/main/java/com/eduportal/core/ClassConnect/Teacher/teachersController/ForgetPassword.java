package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ForgetPassword {
    
    @Autowired
    private JavaMailSender mailSender;

    private final LoginRepo loginRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


    public ForgetPassword(LoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }

    @GetMapping("/teachers/forgetPassword")
    public String forgetPassword(){
        return "teachers/forgetPassword";
    }

    @PostMapping("/teachers/resetPassword")
    public String resetPassword(@RequestParam String email , Model model) {
        String trimEmails = email.trim();

        Optional<LoginModel> optionalLoginModel = loginRepo.findByEmail(trimEmails);

        if (loginRepo.existsByEmail(trimEmails)) {
            LoginModel loginModel = optionalLoginModel.get();

            // Generate reset token and link
            String resetToken = generateResetToken();
            String resetLink = "http://localhost:8080/teachers/reset-password?token=" + resetToken;

            loginModel.setTokenNo(resetToken);
            loginRepo.save(loginModel);


            // Send email
            sendResetEmail(trimEmails, resetLink);
            model.addAttribute("successMessage", "Password reset link has been sent to your email.");
            return "teachers/login"; // Return the page or message
        } else {
            // Handle the case where email does not exist in the database
            model.addAttribute("errorMessage", "Username not found. Please check the email and try again.");

            return "teachers/forgetPassword"; // Optionally redirect to a page that informs the user
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
    @GetMapping("/teachers/reset-password")
    public String showResetPasswordPage(@RequestParam String token, Model model) {
        Optional<LoginModel> optionalLoginModel = loginRepo.findByTokenNo(token);

        if (optionalLoginModel.isPresent()) {
            // Token is valid, display the reset password form
            model.addAttribute("token", token);  // Pass the token to the view
            return "teachers/resetPassword";
        } else {
            // Token is invalid or expired, show an error message
            model.addAttribute("errorMessage", "Invalid or expired token. Please request a new password reset link.");
            return "teachers/LinkExpired"; // Optionally redirect to a page that informs the user
        }
    }
    @PostMapping("/teachers/resetPasswordConfirm")
    public String resetPasswordConfirm(@RequestParam String token,
                                @RequestParam String newPassword,
                                @RequestParam String confirmPassword,
                                Model model) {
        // Check if the passwords match
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Passwords do not match. Please try again.");
            model.addAttribute("token", token); // Retain the token for the form
            return "teachers/resetPassword"; // Stay on the reset password page
        }

        // Check if the token is valid
        Optional<LoginModel> optionalLoginModel = loginRepo.findByTokenNo(token);

        if (optionalLoginModel.isPresent()) {
            LoginModel loginModel = optionalLoginModel.get();

            // Update the password
            loginModel.setPassword(passwordEncoder.encode(newPassword)); // Set the new password (you should hash it)
            loginModel.setTokenNo(null); // Clear the token

            // Save the updated login model
            loginRepo.save(loginModel);

            model.addAttribute("successMessage", "Your password has been reset successfully.");
            return "teachers/login"; // Redirect to login page after password reset
        } else {
            model.addAttribute("errorMessage", "Invalid or expired token. Please request a new password reset link.");
            return "teachers/login"; // Redirect to forget password page
        }
    }

}
