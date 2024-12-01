package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchClassDivision;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchStudentsModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.FetchStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FetchStudentslistController {

    @Autowired
    private FetchStudentsService studentsService;  // Corrected the type and name

    @GetMapping("/teachers/students")
    public String getAllClassDivision(Model model) {
        List<FetchClassDivision> classdivision = studentsService.getAllClassDivision();
        model.addAttribute("class_and_divisions", classdivision);  // Add to model
        return "teachers/students";  // Return the view name
    }

//    @GetMapping("/teachers/students")
//    @ResponseBody  // Forces JSON output for this method
//    public List<FetchClassDivision> getAllClassDivision() {
//        List<FetchClassDivision> classdivision = studentsService.getAllClassDivision();
//        System.out.println("Fetched class divisions: " + classdivision);
//        return classdivision;
//    }

    @GetMapping("/teachers/students_list/{id}")
    public String getAllStudents(@PathVariable int id, Model model) {
        List<FetchStudentsModel> students = studentsService.getAllStudentsByClassDivisionId(id);  // Assuming you filter by `classDivisionId`
        System.out.println("Fetched students for class division ID " + id + ": " + students);
        model.addAttribute("students", students);
        model.addAttribute("classDivisionId", id);
        return "teachers/students_list";
    }


}
