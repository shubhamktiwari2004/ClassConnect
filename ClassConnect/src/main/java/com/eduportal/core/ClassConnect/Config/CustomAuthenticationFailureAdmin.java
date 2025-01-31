package com.eduportal.core.ClassConnect.Config;

import io.lettuce.core.dynamic.annotation.CommandNaming;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureAdmin implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // Redirect to login page with error message as a parameter
        System.out.println("in failure handler.");
        System.out.println("Authentication failed for user: " + exception.getMessage());
        request.getSession().setAttribute("AUTH_ERROR", exception.getMessage());

        response.sendRedirect("/admin/login");
    }
}