package com.eduportal.core.ClassConnect.Config;

import com.eduportal.core.ClassConnect.Students.studentService.StudentLoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandlerStudent implements AuthenticationSuccessHandler {


    private final StudentLoginService studentLoginService;

    public CustomSuccessHandlerStudent(StudentLoginService studentLoginService) {
        this.studentLoginService = studentLoginService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        System.out.println("name student : "+ username);
        String role = authentication.getAuthorities().toString();
        System.out.println("authorities:" + authentication.getAuthorities());

        // Store user details in session
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        studentLoginService.storeUserDataInSession(session, username);
        // Redirect to the index page after login success
        response.sendRedirect("/students/home");
    }
}
