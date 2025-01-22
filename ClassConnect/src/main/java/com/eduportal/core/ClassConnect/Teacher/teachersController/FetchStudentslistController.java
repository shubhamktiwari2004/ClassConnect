package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentMarksModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.FetchStudentsRepository;
import com.eduportal.core.ClassConnect.Teacher.teacherService.FetchStudentsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FetchStudentslistController {

    private final FetchStudentsService fetchStudentsService;  // Corrected the type and name
    private final FetchStudentsRepository fetchStudentsRepository;

    public FetchStudentslistController(FetchStudentsService fetchStudentsService, FetchStudentsRepository fetchStudentsRepository) {
        this.fetchStudentsService = fetchStudentsService;
        this.fetchStudentsRepository = fetchStudentsRepository;
    }

    @GetMapping("/teachers/students")
    public String getAllStudents(Model model , HttpSession session) {
        int teacherId = (int) session.getAttribute("teacher_id");
        List<AddStudentModel> getstudents = fetchStudentsService.getAllStudents(teacherId);
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



    @GetMapping({"/teachers/add_student", "/teachers/add_student/{student_id}"})
    public String getStuendsMarks(@PathVariable(required = false) String student_id, Model model) {
        if (student_id == null) {
            System.out.println("No class division ID provided. Fetching all students.");
        } else {
            Long studentId = Long.parseLong(student_id);
            System.out.println("Fetched students for class division ID " + studentId);
            ArrayList<AddStudentMarksModel> studentsMarks = fetchStudentsService.getStudentMarks(studentId);
            System.out.println("Student Marks: ");
            for (AddStudentMarksModel studentMark : studentsMarks) {
                System.out.println(studentMark); // If toString() is overridden, this will print the relevant details
            }
            List<AddStudentModel> student_data = fetchStudentsService.getStudentById(studentId);  // Fetch more detailed data

            model.addAttribute("marks", studentsMarks);   // Add students to model
            model.addAttribute("student_data", student_data);
            model.addAttribute("id",student_id);
            System.out.println(student_data);
        }
        model.addAttribute("currentPage", "students");
        return "teachers/add_student";
    }


    @GetMapping("/teachers/delete_student/{student_id}")
    public String deleteStudent(@PathVariable Long student_id){
        fetchStudentsService.deleteStudent(student_id);
        return "redirect:/teachers/students";
    }



}
