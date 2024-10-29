package com.eduportal.core.ClassConnect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

//    @RequestMapping("/homes")
//    public String index(Model model) {
//        model.addAttribute("attributeName", "attributeValue");
//        return "index"; // Loads index.html from the templates folder
//    }


    @RequestMapping("/")
    public String greet(){
        return "Welcome to the spring boot.";
    }



}
