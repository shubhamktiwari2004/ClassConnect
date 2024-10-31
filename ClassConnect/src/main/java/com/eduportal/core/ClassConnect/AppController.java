package com.eduportal.core.ClassConnect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

    @RequestMapping("/home")
    public String index() {
        return "index"; // Loads index.html from the templates folder
    }


    @RequestMapping("/")
    @ResponseBody
    public String greet(){
        return "Welcome to the spring boot.";
    }



}
