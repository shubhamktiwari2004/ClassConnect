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
            @RequestParam String teachername,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phoneno,
            Model model) {

        try {
            System.out.println("in profile section1");
            long phoneNo = Long.parseLong(phoneno);
            System.out.print(phoneNo);
            loginService.signUp(teachername, username, email, password , phoneNo);  // Assuming this service handles user registration logic
            return "redirect:/teachers/login"; // Redirect to login page after successful signup
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());  // Add the error message to the model
            return "/teachers/signup";
        }
    }

//    @PostMapping("teachers/login")
//    public String login(
//            @RequestParam String username,
//            @RequestParam String password,
//            Model model){
//
//        try {
//            loginService.login(username, password);  // Assuming this service handles user registration logic
//            return "/teachers/index";// Redirect to login page after successful signup
//        }catch (Exception e) {
//        model.addAttribute("errorMessage", e.getMessage());  // Add the error message to the model
//        return "/teachers/login";
//        }
//    }




}
