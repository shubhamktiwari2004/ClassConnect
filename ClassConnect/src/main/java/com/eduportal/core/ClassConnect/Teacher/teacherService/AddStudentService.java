package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.AddStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddStudentService {

    @Autowired
    AddStudentRepository repository;

    public AddStudentModel saveStudent(AddStudentModel student) {

        return repository.save(student);
    }
}
