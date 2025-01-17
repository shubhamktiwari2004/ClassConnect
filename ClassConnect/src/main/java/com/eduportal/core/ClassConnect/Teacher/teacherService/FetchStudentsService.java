package com.eduportal.core.ClassConnect.Teacher.teacherService;

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

    private final AddStudentMarksRepo addStudentMarksRepo;

    public FetchStudentsService(FetchStudentsRepository fetchStudentsRepository, AddStudentMarksRepo addStudentMarksRepo) {
        this.fetchStudentsRepository = fetchStudentsRepository;
        this.addStudentMarksRepo = addStudentMarksRepo;
    }

    public List<AddStudentModel> getStudentById(int id) {
        return fetchStudentsRepository.findStudentById(id);
    }

    public ArrayList<AddStudentMarksModel> getStudentMarks(int id) {
        return addStudentMarksRepo.findStudentMarks(id);
    }
    public List<AddStudentModel> getAllStudents(int teacherId) {
        List<AddStudentModel> addStudentModel = fetchStudentsRepository.getAllStudents(teacherId);
        System.out.println("Fetched class divisions: " + addStudentModel);
        return addStudentModel;
    }

    public void deleteStudent(int id){
        fetchStudentsRepository.deleteStudent(id);
    }



}
