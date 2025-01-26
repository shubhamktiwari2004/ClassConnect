package com.eduportal.core.ClassConnect.Config;

import com.eduportal.core.ClassConnect.Admin.AdminModel.AdminLoginModel;
import com.eduportal.core.ClassConnect.Admin.AdminRepo.AdminLoginRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(basePackages = "com.eduportal.core.ClassConnect.Admin")
public class GlobalControllerAdivceAdmin {


    private final AdminLoginRepo adminLoginRepo;  // Assuming this is your JPA repository

    public GlobalControllerAdivceAdmin(AdminLoginRepo adminLoginRepo) {
        this.adminLoginRepo = adminLoginRepo;
    }


    @ModelAttribute("adminData")
    public AdminLoginModel populateTeacherData(HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");  // Get the teacher_id as Integer
        if (adminId != null) {
            return  adminLoginRepo.findByAdminId(adminId); // Convert to long
        }
        return new AdminLoginModel();
    }
}
