package com.eduportal.core.ClassConnect.Config;

import com.eduportal.core.ClassConnect.Students.studentRepository.StudentLoginRepo;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;

@ControllerAdvice(basePackages = "com.eduportal.core.ClassConnect.Students")
public class GlobalControllerAdviceStudent {

    private final StudentLoginRepo studentLoginRepo;

    public GlobalControllerAdviceStudent(StudentLoginRepo studentLoginRepo) {
        this.studentLoginRepo = studentLoginRepo;
    }

    @ModelAttribute("studentData")
    public ArrayList<StudentLoginModel> studentData(HttpSession session){
        System.out.println("teacher_id"+session.getAttribute("teacher_id"));
        System.out.println("teacher_id"+session.getAttribute("studentId"));
        System.out.println("teacher_id"+session.getAttribute("username"));
        Long studentId = (Long) session.getAttribute("studentId");  // Get the teacher_id as Integer
        if (studentId != null) {
            return studentLoginRepo.findStudentById(studentId); // Convert to long
        }
        return new ArrayList<>();
    }
}
