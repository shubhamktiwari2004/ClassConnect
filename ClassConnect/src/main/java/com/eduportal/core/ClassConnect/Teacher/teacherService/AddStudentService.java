package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentMarksModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.AddStudentMarksRepo;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.AddStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddStudentService {

    @Autowired
    AddStudentRepository addStudentRepository;

    @Autowired
    AddStudentMarksRepo addStudentMarksRepo;

    public void saveStudent(AddStudentModel student) {
        if (student.getTeacher_id() == null) {
            throw new IllegalArgumentException("Teacher ID cannot be null");
        }
        addStudentRepository.save(student);
    }
    public void saveMarks(AddStudentMarksModel marks){
        addStudentMarksRepo.save(marks);
    }

}
