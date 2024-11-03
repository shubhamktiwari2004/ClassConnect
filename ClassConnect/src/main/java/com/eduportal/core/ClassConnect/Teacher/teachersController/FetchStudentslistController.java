package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchStudentsModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.FetchStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FetchStudentslistController {

    @Autowired
    private FetchStudentsService studentsService;  // Corrected the type and name


    @GetMapping("/teachers/students_list")
    public String getAllStudents(Model model){
        List<FetchStudentsModel> students = studentsService.getAllStudents();
        System.out.println("Fetched schedules: " + students); // Add this log
        model.addAttribute("students", students);
        return "teachers/students_list";
//        return studentsService.getAllStudents();
    }

}
