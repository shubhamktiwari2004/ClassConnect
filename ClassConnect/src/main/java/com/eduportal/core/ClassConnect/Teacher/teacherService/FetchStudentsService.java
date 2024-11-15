package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchClassDivision;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchStudentsModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.FetchStudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchStudentsService {

    @Autowired
    private FetchStudentsRepository studentsRepository;

    public List<FetchStudentsModel> getAllStudentsByClassDivisionId(int id) {
        return studentsRepository.findStudentsByClassDivisionId(id);
    }
    public List<FetchClassDivision> getAllClassDivision() {
        List<FetchClassDivision> classDivisions = studentsRepository.getAllClassDivision();
        System.out.println("Fetched class divisions: " + classDivisions);
        return classDivisions;
    }


}
