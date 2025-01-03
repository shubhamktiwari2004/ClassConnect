package com.eduportal.core.ClassConnect.Config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import org.springframework.security.core.AuthenticationException;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {



    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // Redirect to login page with error message as a parameter
        System.out.println("in failure handler.");
        System.out.println("Authentication failed for user: " + exception.getMessage());

        response.sendRedirect(request.getContextPath() + "/teachers/login?error=true");
    }
}
