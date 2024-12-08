package com.eduportal.core.ClassConnect.Config;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.LoginRepo;
import com.eduportal.core.ClassConnect.Teacher.teacherService.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.time.LocalDateTime;


@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private LoginService loginService;
    private LoginRepo loginRepo;

    public CustomLogoutSuccessHandler(LoginService loginService, LoginRepo loginRepo) {
        this.loginService = loginService;
        this.loginRepo = loginRepo;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String username = authentication.getName();

        LoginModel user = loginService.getUserByUsername(username);
        if (user != null) {
            System.out.println("inside logout");
            user.setLogoutTimestamp(LocalDateTime.now());
            loginService.saveUser(user);
        }

        request.getSession().invalidate();
        response.sendRedirect("/teachers/login");
    }
}

