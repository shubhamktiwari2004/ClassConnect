package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Students.studentRepository.AddStudentThroTeacherRepo;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentMarksModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.AddStudentMarksRepo;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.FetchStudentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class FetchStudentsService {


    private final FetchStudentsRepository fetchStudentsRepository;

    private final AddStudentThroTeacherRepo addStudentThroTeacherRepo;

    private final AddStudentMarksRepo addStudentMarksRepo;

    public FetchStudentsService(FetchStudentsRepository fetchStudentsRepository, AddStudentThroTeacherRepo addStudentThroTeacherRepo, AddStudentMarksRepo addStudentMarksRepo) {
        this.fetchStudentsRepository = fetchStudentsRepository;
        this.addStudentThroTeacherRepo = addStudentThroTeacherRepo;
        this.addStudentMarksRepo = addStudentMarksRepo;
    }

    public List<AddStudentModel> getStudentById(Long student_id) {
        return fetchStudentsRepository.findStudentById(student_id);
    }

    public ArrayList<AddStudentMarksModel> getStudentMarks(Long student_id) {
        return addStudentMarksRepo.findStudentMarks(student_id);
    }
    public List<AddStudentModel> getAllStudents(int teacherId) {
        List<AddStudentModel> addStudentModel = fetchStudentsRepository.getAllStudents(teacherId);
        System.out.println("Fetched class divisions: " + addStudentModel);
        return addStudentModel;
    }

    public void deleteStudent(Long student_id){
        fetchStudentsRepository.deleteStudent(student_id);
        addStudentThroTeacherRepo.deleteStudent(student_id);
    }



}
