package com.eduportal.core.ClassConnect.Admin.AdminService;

import com.eduportal.core.ClassConnect.Admin.AdminModel.AdminLoginModel;
import com.eduportal.core.ClassConnect.Admin.AdminRepo.AdminLoginRepo;
import com.eduportal.core.ClassConnect.Students.studentsModel.AddStudentThroughTeacher;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminLoginService implements UserDetailsService {

    private final AdminLoginRepo adminLoginRepo;

    public AdminLoginService(AdminLoginRepo adminLoginRepo) {
        this.adminLoginRepo = adminLoginRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.print("i am there");

        AdminLoginModel adminLoginModel = adminLoginRepo.findByUsername(username)
                .or(() -> adminLoginRepo.findByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        System.out.print("i am here");
        return User.builder()
                .username(adminLoginModel.getUsername())
                .password(adminLoginModel.getPassword())
                .roles("ADMIN")
                .build();
    }


    public void storeUserDataInSession(HttpSession session, String username) {
        AdminLoginModel user = adminLoginRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        session.setAttribute("username", user.getUsername());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("adminId", user.getAdminId());
        session.setAttribute("sessionId", session.getId());

        user.setLoginTimestamp(LocalDateTime.now());
        user.setSessionId(session.getId());
        adminLoginRepo.save(user);


    }

    //when logout chechking if the username is not null
    public AdminLoginModel getUserByUsername(String username) {
        return adminLoginRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }



    public void saveUser(AdminLoginModel model) {
        adminLoginRepo.save(model);
    }
}
