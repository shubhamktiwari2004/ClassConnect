package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchStudentsModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.FetchStudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchStudentsService {

    @Autowired
    private FetchStudentsRepository fetchStudentsRepository;

    public List<AddStudentModel> getStudentById(int id) {
        return fetchStudentsRepository.findStudentById(id);
    }

    public List<FetchStudentsModel> getStudentMarks(int id) {
        return fetchStudentsRepository.findStudentMarks(id);
    }
    public List<AddStudentModel> getAllStudents() {
        List<AddStudentModel> addStudentModel = fetchStudentsRepository.getAllStudents();
        System.out.println("Fetched class divisions: " + addStudentModel);
        return addStudentModel;
    }



}
