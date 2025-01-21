package com.eduportal.core.ClassConnect.Students.studentsController;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentsAppController {

    @RequestMapping("/student/home")
    public String index() {
        return "students/index"; // Loads index.html from the templates folder
    }

    @RequestMapping("/student/schedule")
    public String schedule() {
        return "students/schedule"; // Loads index.html from the templates folder
    }

    @RequestMapping("/student/performance")
    public String performance() {
        return "students/performance"; // Loads index.html from the templates folder
    }

}
