package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;


    @GetMapping("teachers/signup")
    public String signUp(){
        return "teachers/signup";
    }

    @GetMapping("teachers/login")
    public String login(){
        return "teachers/login";
    }


    @PostMapping("teachers/signup")
    public String signUp(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        try {
            loginService.signUp(username, email, password);  // Assuming this service handles user registration logic
            return "/teachers/login"; // Redirect to login page after successful signup
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());  // Add the error message to the model
            return "/teachers/signup";
        }
    }

    @PostMapping("teachers/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model){

        try {
            loginService.login(username, password);  // Assuming this service handles user registration logic
            return "/teachers/index";// Redirect to login page after successful signup
        }catch (Exception e) {
        model.addAttribute("errorMessage", e.getMessage());  // Add the error message to the model
        return "/teachers/login";
        }
    }




}
