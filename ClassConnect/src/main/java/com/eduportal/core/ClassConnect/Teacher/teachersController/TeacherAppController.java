package com.eduportal.core.ClassConnect.Teacher.teachersController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeacherAppController {

    @RequestMapping("/teachers/home")
    public String index() {
        return "teachers/index"; // Loads index.html from the templates folder
    }

    @RequestMapping("/teachers/schedule")
    public String schedule() {
        return "teachers/schedule"; // Loads index.html from the templates folder
    }

    @RequestMapping("/teachers/students")
    public String performance() {
        return "teachers/students"; // Loads index.html from the templates folder
    }
    @RequestMapping("/teachers/addSchedule")
    public String addSchedule() {
        return "teachers/addSchedule"; // Loads index.html from the templates folder
    }

    @RequestMapping("/teachers")
    @ResponseBody
    public String greet(){
        return "Welcome to the teacher page.";
    }
}
