package com.eduportal.core.ClassConnect.Students.studentService;

import com.eduportal.core.ClassConnect.Students.studentRepository.AddStudentThroTeacherRepo;
import com.eduportal.core.ClassConnect.Students.studentRepository.StudentLoginRepo;
import com.eduportal.core.ClassConnect.Students.studentsModel.AddStudentThroughTeacher;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class StudentLoginService implements UserDetailsService {

    private final StudentLoginRepo studentLoginRepo;
    private final AddStudentThroTeacherRepo addStudentThroTeacherRepo;

    public StudentLoginService(StudentLoginRepo studentLoginRepo, AddStudentThroTeacherRepo addStudentThroTeacherRepo) {
        this.studentLoginRepo = studentLoginRepo;
        this.addStudentThroTeacherRepo = addStudentThroTeacherRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        StudentLoginModel studentLoginModel = studentLoginRepo.findByUsername(username)
                    .or(() -> studentLoginRepo.findByEmail(username))
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.builder()
                .username(studentLoginModel.getUsername())
                .password(studentLoginModel.getPassword())
                .roles("STUDENT")
                .build();
    }


    public void storeUserDataInSession(HttpSession session, String username) {
        StudentLoginModel user = studentLoginRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        AddStudentThroughTeacher model = addStudentThroTeacherRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        session.setAttribute("username", user.getUsername());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("teacher_id", user.getTeacher_id());
        session.setAttribute("sessionId", session.getId());
        session.setAttribute("firstName", user.getFirstName());
        session.setAttribute("lastName", user.getLastName());
        session.setAttribute("studentId", user.getStudent_id());

        model.setLogin_stamp(LocalDateTime.now());
        model.setSession_id(session.getId());
        addStudentThroTeacherRepo.save(model);


    }

    //when logout chechking if the username is not null
    public StudentLoginModel getUserByUsername(String username) {
        return studentLoginRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public AddStudentThroughTeacher getStudentUser(String username) {
        return addStudentThroTeacherRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }



    public void saveUser(AddStudentThroughTeacher model) {
        addStudentThroTeacherRepo.save(model);
    }

}
