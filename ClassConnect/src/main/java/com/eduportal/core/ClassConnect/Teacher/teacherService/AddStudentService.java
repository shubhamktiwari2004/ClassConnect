package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentMarksModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.AddStudentMarksRepo;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.AddStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AddStudentService {

    private final AddStudentRepository addStudentRepository;

    private final AddStudentMarksRepo addStudentMarksRepo;

    public AddStudentService(AddStudentRepository addStudentRepository, AddStudentMarksRepo addStudentMarksRepo) {
        this.addStudentRepository = addStudentRepository;
        this.addStudentMarksRepo = addStudentMarksRepo;
    }

    public void saveStudent(AddStudentModel student) {
        if (student.getTeacher_id() == null) {
            throw new IllegalArgumentException("Teacher ID cannot be null");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));

        addStudentRepository.save(student);
    }
    public void saveMarks(AddStudentMarksModel marks){
        addStudentMarksRepo.save(marks);
    }

}
