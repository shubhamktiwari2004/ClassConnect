package com.eduportal.core.ClassConnect.Teacher.teacherService;

import org.springframework.stereotype.Service;

@Service
public class AddStudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(AddStudentModel studentModel) {
        // Map AddStudentModel to Student entity
        Student student = new Student();
        student.setStudentName(studentModel.getStudentName());
        student.setMathsMarks(studentModel.getMathsMarks());
        student.setScienceMarks(studentModel.getScienceMarks());
        student.setHindiMarks(studentModel.getHindiMarks());
        student.setEnglishMarks(studentModel.getEnglishMarks());
        student.setTotalMarks(studentModel.getTotalMarks());

        studentRepository.save(student);
    }
}
