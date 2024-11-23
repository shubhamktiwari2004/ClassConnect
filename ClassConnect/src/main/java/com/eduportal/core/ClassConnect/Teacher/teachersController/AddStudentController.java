package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.models.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Use @RestController to avoid @ResponseBody on each method
@RequestMapping("/students")
public class AddStudentController {

    private final StudentService studentService;

    @Autowired
    public AddStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody AddStudentModel studentModel) {
        studentService.saveStudent(studentModel);
        return ResponseEntity.ok("Student added successfully!");
    }
}
