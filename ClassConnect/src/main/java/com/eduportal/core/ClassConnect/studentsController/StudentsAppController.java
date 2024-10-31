package com.eduportal.core.ClassConnect.studentsController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentsAppController {

    @RequestMapping("/home")
    public String index() {
        return "students/index"; // Loads index.html from the templates folder
    }

    @RequestMapping("/schedule")
    public String schedule() {
        return "students/schedule"; // Loads index.html from the templates folder
    }

    @RequestMapping("/performance")
    public String performance() {
        return "students/performance"; // Loads index.html from the templates folder
    }

    @RequestMapping("/")
    @ResponseBody
    public String greet(){
        return "Welcome to the student page.";
    }



}
