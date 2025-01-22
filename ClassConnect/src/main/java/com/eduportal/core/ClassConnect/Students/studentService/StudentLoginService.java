package com.eduportal.core.ClassConnect.Students.studentService;

import com.eduportal.core.ClassConnect.Students.studentRepository.StudentLoginRepo;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentLoginService implements UserDetailsService {

    private final StudentLoginRepo studentLoginRepo;

    public StudentLoginService(StudentLoginRepo studentLoginRepo) {
        this.studentLoginRepo = studentLoginRepo;
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

        session.setAttribute("username", user.getUsername());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("teacher_id", user.getTeacher_id());
        session.setAttribute("sessionId", session.getId());
        session.setAttribute("firstName", user.getFirstName());
        session.setAttribute("lastName", user.getLastName());
        session.setAttribute("studentId", user.getStudent_id());

    }

    //when logout chechking if the username is not null
    public StudentLoginModel getUserByUsername(String username) {
        return studentLoginRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
