package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.AddStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/teachers")
public class AddStudentController {

    @Autowired
    private AddStudentService service;

    @PostMapping("/saveStudent")
    public ResponseEntity<String> saveStudent(@RequestBody Map<String, Object> requestData) {
        // Log incoming data
        System.out.println("Received Data: " + requestData);

        // Check for missing or null values and handle accordingly
        if (!requestData.containsKey("classDivisionsId") || requestData.get("classDivisionsId") == null) {
            return ResponseEntity.badRequest().body("Missing or null value for 'classDivisionsId'");
        }
        if (!requestData.containsKey("RollNo") || requestData.get("RollNo") == null) {
            return ResponseEntity.badRequest().body("Missing or null value for 'classDivisionsId'");
        }
        if (!requestData.containsKey("studentName") || requestData.get("studentName") == null) {
            return ResponseEntity.badRequest().body("Missing or null value for 'studentName'");
        }
        if (!requestData.containsKey("mathsMarks") || requestData.get("mathsMarks") == null) {
            return ResponseEntity.badRequest().body("Missing or null value for 'mathsMarks'");
        }
        if (!requestData.containsKey("scienceMarks") || requestData.get("scienceMarks") == null) {
            return ResponseEntity.badRequest().body("Missing or null value for 'scienceMarks'");
        }
        if (!requestData.containsKey("hindiMarks") || requestData.get("hindiMarks") == null) {
            return ResponseEntity.badRequest().body("Missing or null value for 'hindiMarks'");
        }
        if (!requestData.containsKey("englishMarks") || requestData.get("englishMarks") == null) {
            return ResponseEntity.badRequest().body("Missing or null value for 'englishMarks'");
        }
        if (!requestData.containsKey("totalMarks") || requestData.get("totalMarks") == null) {
            return ResponseEntity.badRequest().body("Missing or null value for 'totalMarks'");
        }

        // Now, safely access the data and convert
        AddStudentModel student = new AddStudentModel();

        try {
            student.setClassDivisionsId(Long.valueOf(requestData.get("classDivisionsId").toString()));
            student.setStudentName(requestData.get("studentName").toString());
            student.setRollNo(Integer.parseInt(requestData.get("RollNo").toString()));
            student.setMathsMarks(Integer.parseInt(requestData.get("mathsMarks").toString()));
            student.setScienceMarks(Integer.parseInt(requestData.get("scienceMarks").toString()));
            student.setHindiMarks(Integer.parseInt(requestData.get("hindiMarks").toString()));
            student.setEnglishMarks(Integer.parseInt(requestData.get("englishMarks").toString()));
            student.setTotalMarks(Integer.parseInt(requestData.get("totalMarks").toString()));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Error converting numerical values: " + e.getMessage());
        }

        // Call the service to save the student data
        AddStudentModel savedStudent = service.saveStudent(student);

        // Respond back for confirmation
        return ResponseEntity.ok("Student saved successfully! ID: " + savedStudent.getId());
    }
}
