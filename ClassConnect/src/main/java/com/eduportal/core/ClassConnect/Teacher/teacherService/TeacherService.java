package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.TeacherModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherModel> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public TeacherModel getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public TeacherModel saveTeacher(TeacherModel teacher) {
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
