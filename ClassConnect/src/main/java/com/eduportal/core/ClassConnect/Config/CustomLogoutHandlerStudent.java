package com.eduportal.core.ClassConnect.Config;

import com.eduportal.core.ClassConnect.Students.studentRepository.StudentLoginRepo;
import com.eduportal.core.ClassConnect.Students.studentService.StudentLoginService;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.LoginRepo;
import com.eduportal.core.ClassConnect.Teacher.teacherService.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component

public class CustomLogoutHandlerStudent implements LogoutSuccessHandler {

    private final StudentLoginService studentLoginService;
    private final StudentLoginRepo studentLoginRepo;

    public CustomLogoutHandlerStudent(StudentLoginService studentLoginService, StudentLoginRepo studentLoginRepo) {
        this.studentLoginService = studentLoginService;
        this.studentLoginRepo = studentLoginRepo;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String username = authentication.getName();
        System.out.println("hello:::"+username);
        StudentLoginModel user = studentLoginService.getUserByUsername(username);
        if (user != null) {
            System.out.println("inside logout");
//            user.setLogoutTimestamp(LocalDateTime.now());
//            studentLoginService.saveUser(user);
        }

        request.getSession().invalidate();
        response.sendRedirect("/students/login");
    }
}
