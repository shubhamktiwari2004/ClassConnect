package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentMarksModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchStudentsModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.FetchStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FetchStudentslistController {

    @Autowired
    private FetchStudentsService fetchStudentsService;  // Corrected the type and name

    @GetMapping("/teachers/students")
    public String getAllStudents(Model model) {
        List<AddStudentModel> getstudents = fetchStudentsService.getAllStudents();
        if (getstudents == null || getstudents.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Updated Student Image URLs: " + getstudents.get(0).getImage_url());
            System.out.println("Updated Student Image URLs: " + getstudents);
        }
        model.addAttribute("students", getstudents);
        model.addAttribute("currentPage", "students");

        return "teachers/students";
    }



    @GetMapping({"/teachers/add_student", "/teachers/add_student/{id}"})
    public String getStuendsMarks(@PathVariable(required = false) Integer id, Model model) {
        if (id == null) {
            System.out.println("No class division ID provided. Fetching all students.");
        } else {
            System.out.println("Fetched students for class division ID " + id);
            ArrayList<AddStudentMarksModel> studentsMarks = fetchStudentsService.getStudentMarks(id);
            System.out.println("Student Marks: ");
            for (AddStudentMarksModel studentMark : studentsMarks) {
                System.out.println(studentMark); // If toString() is overridden, this will print the relevant details
            }
            List<AddStudentModel> student_data = fetchStudentsService.getStudentById(id);  // Fetch more detailed data

            model.addAttribute("marks", studentsMarks);   // Add students to model
            model.addAttribute("student_data", student_data);
            model.addAttribute("id",id);
            System.out.println(student_data);
        }
        model.addAttribute("currentPage", "students");
        return "teachers/add_student";
    }




}
