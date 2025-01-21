package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentMarksModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.FetchStudentsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    private final FetchStudentsService fetchStudentsService;

    public DashboardController(FetchStudentsService fetchStudentsService) {
        this.fetchStudentsService = fetchStudentsService;
    }

    @GetMapping("/teachers/home")
    public String getStudents(Model model, HttpSession session) {
        Integer teacherId = (Integer) session.getAttribute("teacher_id");
        List<AddStudentModel> students = fetchStudentsService.getAllStudents(teacherId);
        if (students == null || students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Updated Student Image URLs: " + students.get(0).getImage_url());
            System.out.println("Updated Student Image URLs: " + students);
        }
        System.out.println("List of Students"+students);
        model.addAttribute("students",students);

        return "teachers/index";
    }
    @GetMapping("/teachers/home/{studentId}")
    @ResponseBody // Add this to ensure the response is JSON
    public List<AddStudentMarksModel> getStudentMarksById(@PathVariable Integer studentId) {
        System.out.println("Fetching marks for student ID: " + studentId);

        // Fetch student marks using the provided student ID
        ArrayList<AddStudentMarksModel> studentsMarks = fetchStudentsService.getStudentMarks(studentId);

        if (studentsMarks != null && !studentsMarks.isEmpty()) {
            System.out.println("Student Marks: ");
            studentsMarks.forEach(studentMark -> System.out.println(studentMark)); // Output student marks
        }
        return studentsMarks; // Returning marks as JSON
    }

}

