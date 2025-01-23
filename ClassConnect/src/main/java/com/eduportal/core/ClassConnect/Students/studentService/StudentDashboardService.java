package com.eduportal.core.ClassConnect.Students.studentService;

import com.eduportal.core.ClassConnect.Students.studentRepository.StudentDashboardRepo;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentDashboardModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDashboardService {

    private final StudentDashboardRepo studentDashboardRepo;

    public StudentDashboardService(StudentDashboardRepo studentDashboardRepo) {
        this.studentDashboardRepo = studentDashboardRepo;
    }


    public List<StudentDashboardModel> getStudentMarksById(Long studentId)
    {
        if (studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        return studentDashboardRepo.findStudentMarksById(studentId);
    }
}
