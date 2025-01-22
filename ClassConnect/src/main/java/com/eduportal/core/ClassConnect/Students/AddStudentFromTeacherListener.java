package com.eduportal.core.ClassConnect.Students;

import com.eduportal.core.ClassConnect.Students.studentRepository.AddStudentThroTeacherRepo;
import com.eduportal.core.ClassConnect.Students.studentsModel.AddStudentThroughTeacher;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AddStudentFromTeacherListener {

    private final AddStudentThroTeacherRepo addStudentThroTeacherRepo;

    @Autowired
    public AddStudentFromTeacherListener(@Lazy AddStudentThroTeacherRepo addStudentThroTeacherRepo) {
        this.addStudentThroTeacherRepo = addStudentThroTeacherRepo;
    }

    @PostPersist // Triggered after a new AddStudentModel entity is persisted
    public void copyToStudentLoginDetails(AddStudentModel student) {
        // Create a new StudentLoginDetails entity
        AddStudentThroughTeacher loginDetails = new AddStudentThroughTeacher();
        loginDetails.setStudent_id(student.getStudent_id());
        loginDetails.setTeacher_id(student.getTeacher_id());
        loginDetails.setEmail(student.getEmail());
        loginDetails.setUsername(student.getUser_name());
        loginDetails.setIs_active(student.getIs_active());
        loginDetails.setPassword(student.getPassword());

        // Generate and set additional fields
        loginDetails.setSession_id(null);
        loginDetails.setLogin_stamp(null); // Set the current timestamp for login
        loginDetails.setLogout_stamp(null); // Leave logout stamp null initially
        loginDetails.setRole("STUDENT"); // Set a default role

        // Save the new StudentLoginDetails entity
        addStudentThroTeacherRepo.save(loginDetails);
    }
}
