package com.eduportal.core.ClassConnect.Students.studentsController;

import com.eduportal.core.ClassConnect.Students.studentService.StudentDashboardService;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentDashboardModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class StudentDashboardController {

    private final StudentDashboardService studentDashboardService;

    public StudentDashboardController(StudentDashboardService studentDashboardService) {
        this.studentDashboardService = studentDashboardService;
    }

    //Function to get dashboard marks of specific students
    @GetMapping("/students/marks")
    @ResponseBody
    public ResponseEntity<?> studentMarksById(HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "Student ID not found in session"));
        }
        List<StudentDashboardModel> studentsMarks = studentDashboardService.getStudentMarksById(studentId);
        return ResponseEntity.ok(studentsMarks);
    }
}
