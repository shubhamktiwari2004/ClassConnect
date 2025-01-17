package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Config.GlobalControllerAdvice;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeacherAppController {

    private final GlobalControllerAdvice globalControllerAdvice;

    public TeacherAppController(GlobalControllerAdvice globalControllerAdvice) {
        this.globalControllerAdvice = globalControllerAdvice;
    }

    @RequestMapping("/teachers/home")
    public String index(Model model , HttpSession session) {
//        globalControllerAdvice.populateTeacherData(session);
        return "teachers/index"; // Loads index.html from the templates folder
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
