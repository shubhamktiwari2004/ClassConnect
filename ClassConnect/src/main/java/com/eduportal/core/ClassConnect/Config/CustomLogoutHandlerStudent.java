package com.eduportal.core.ClassConnect.Config;

import com.eduportal.core.ClassConnect.Students.studentRepository.AddStudentThroTeacherRepo;
import com.eduportal.core.ClassConnect.Students.studentRepository.StudentLoginRepo;
import com.eduportal.core.ClassConnect.Students.studentService.StudentLoginService;
import com.eduportal.core.ClassConnect.Students.studentsModel.AddStudentThroughTeacher;
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

    public CustomLogoutHandlerStudent(StudentLoginService studentLoginService) {
        this.studentLoginService = studentLoginService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String username = authentication.getName();
        System.out.println("hello:::"+username);
        StudentLoginModel user = studentLoginService.getUserByUsername(username);
        AddStudentThroughTeacher model = studentLoginService.getStudentUser(username);
        if (user != null) {
            System.out.println("inside logout");
            model.setLogout_stamp(LocalDateTime.now());
            studentLoginService.saveUser(model);
        }

        request.getSession().invalidate();
        response.sendRedirect("/students/login");
    }
}
