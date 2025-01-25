package com.eduportal.core.ClassConnect.Config;

import com.eduportal.core.ClassConnect.Admin.AdminService.AdminLoginService;
import com.eduportal.core.ClassConnect.Teacher.teacherService.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@Component
public class CustomSuccessHandlerAdmin implements AuthenticationSuccessHandler {
    private final AdminLoginService adminLoginService;

    public CustomSuccessHandlerAdmin(AdminLoginService adminLoginService) {
        this.adminLoginService = adminLoginService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String username = authentication.getName();
        String role = authentication.getAuthorities().toString();
        System.out.println("authorities:" + authentication.getAuthorities());

        // Store user details in session
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        adminLoginService.storeUserDataInSession(session, username);
        // Redirect to the index page after login success
        response.sendRedirect("/admin/home");
    }
}
