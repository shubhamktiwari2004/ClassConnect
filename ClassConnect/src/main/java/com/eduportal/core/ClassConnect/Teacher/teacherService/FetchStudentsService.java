package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentMarksModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchStudentsModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.AddStudentMarksRepo;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.FetchStudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FetchStudentsService {

    @Autowired
    private FetchStudentsRepository fetchStudentsRepository;
    @Autowired
    private AddStudentMarksRepo addStudentMarksRepo;

    public List<AddStudentModel> getStudentById(int id) {
        return fetchStudentsRepository.findStudentById(id);
    }

    public ArrayList<AddStudentMarksModel> getStudentMarks(int id) {
        System.out.println("hello :"+id);
        System.out.println("hello :"+addStudentMarksRepo.findStudentMarks(id));
        return addStudentMarksRepo.findStudentMarks(id);
    }
    public List<AddStudentModel> getAllStudents() {
        List<AddStudentModel> addStudentModel = fetchStudentsRepository.getAllStudents();
        System.out.println("Fetched class divisions: " + addStudentModel);
        return addStudentModel;
    }



}
