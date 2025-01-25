package com.eduportal.core.ClassConnect.Config;
import com.eduportal.core.ClassConnect.Admin.AdminModel.AdminLoginModel;
import com.eduportal.core.ClassConnect.Admin.AdminRepo.AdminLoginRepo;
import com.eduportal.core.ClassConnect.Admin.AdminService.AdminLoginService;
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
public class CustomLogoutHandlerAdmin implements LogoutSuccessHandler {

    private final AdminLoginService adminLoginService;
    private final AdminLoginRepo adminLoginRepo;

    public CustomLogoutHandlerAdmin(AdminLoginService adminLoginService, AdminLoginRepo adminLoginRepo) {
        this.adminLoginService = adminLoginService;
        this.adminLoginRepo = adminLoginRepo;
    }


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String username = authentication.getName();

        AdminLoginModel user = adminLoginService.getUserByUsername(username);
        if (user != null) {
            System.out.println("inside logout");
            user.setLogoutTimestamp(LocalDateTime.now());
            adminLoginService.saveUser(user);
        }

        request.getSession().invalidate();
        response.sendRedirect("/admin/login");
    }
}

