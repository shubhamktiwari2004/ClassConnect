package com.eduportal.core.ClassConnect.Teacher.teachersController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TeacherAppController {

    @RequestMapping("/teachers/home")
    public String index(HttpSession session,Model model) {

        Integer teacherId = (Integer) session.getAttribute("teacher_id");
        String emailId = (String) session.getAttribute("email");
        String nameId = (String) session.getAttribute("teacherName");

        Map<String, String> prof = new HashMap<>();
        prof.put("teacher_id", String.valueOf(teacherId));
        prof.put("email", emailId);
        prof.put("teacher_name", nameId);

        System.out.println("hi i am here :"+prof);

        model.addAttribute("profile", prof);

        return "teachers/index"; // Loads index.html from the templates folder
    }

//    @RequestMapping("/teachers/schedule")
//    public String schedule() {
//        return "teachers/schedule"; // Loads index.html from the templates folder
//    }

    @RequestMapping("/teachers/students")
    public String performance() {
        return "teachers/students"; // Loads index.html from the templates folder
    }
    @RequestMapping("/teachers/addSchedule")
    public String addSchedule() {
        return "teachers/addSchedule"; // Loads index.html from the templates folder
    }

//    @RequestMapping("/teachers/students_list")
//    public String student_list() {
//        return "teachers/students_list"; // Loads index.html from the templates folder
//    }
//
    @RequestMapping("/teachers")
    @ResponseBody
    public String greet(){
        return "Welcome to the teacher page.";
    }
}
