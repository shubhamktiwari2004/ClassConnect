package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchStudentsModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.FetchStudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchStudentsService {

    @Autowired
    private FetchStudentsRepository studentsRepository;

    public List<FetchStudentsModel> getAllStudents() {
        return studentsRepository.getAllStudents();
    }


}
