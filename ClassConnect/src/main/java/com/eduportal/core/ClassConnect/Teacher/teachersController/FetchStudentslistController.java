package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchClassDivision;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchStudentsModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.AddStudentRepository;
import com.eduportal.core.ClassConnect.Teacher.teacherService.FetchStudentsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;

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



    @GetMapping("/teachers/add_student/{id}")
    public String getAllStudents(@PathVariable int id, Model model) {
        List<FetchStudentsModel> students = fetchStudentsService.getAllStudentsByClassDivisionId(id);  // Assuming you filter by `classDivisionId`
        System.out.println("Fetched students for class division ID " + id + ": " + students);
        model.addAttribute("students", students);
        model.addAttribute("classDivisionId", id);
        model.addAttribute("currentPage", "students");
        return "teachers/add_student";
    }


}
