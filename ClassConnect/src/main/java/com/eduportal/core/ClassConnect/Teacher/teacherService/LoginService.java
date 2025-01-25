package com.eduportal.core.ClassConnect.Teacher.teacherService;
import com.eduportal.core.ClassConnect.Api.ApiResponse;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.RegistrationProfileDetails;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.LoginRepo;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.RegistrationProfileDetailsRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class LoginService implements UserDetailsService {

    private final LoginRepo loginRepo;
    private final RegistrationProfileDetailsRepo profileDetailsRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public LoginService(LoginRepo loginRepo ,RegistrationProfileDetailsRepo profileDetailsRepo) {
        this.loginRepo = loginRepo;
        this.profileDetailsRepo = profileDetailsRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to load the user by username, if not found, check by email
        LoginModel loginModel = loginRepo.findByUsername(username)
                .or(() -> loginRepo.findByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));



        return User.builder()
                .username(loginModel.getUsername())
                .password(loginModel.getPassword()) // Ideally, encode the password
                .roles("TEACHER") // Add roles if applicable
                .build();
    }

    //storing session and login time stamp when login
    public void storeUserDataInSession(HttpSession session, String username) {

        LoginModel user = loginRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        RegistrationProfileDetails profileDetails = profileDetailsRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        session.setAttribute("username", user.getUsername());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("teacher_id", user.getTeacher_id());
        session.setAttribute("sessionId", session.getId());
        session.setAttribute("teacherName", profileDetails.getName());

        System.out.println("setting session id inside table.");
        user.setSession_id(session.getId());
        user.setLoginTimestamp(LocalDateTime.now());
        loginRepo.save(user);
    }

    //when logout chechking if the username is not null
    public LoginModel getUserByUsername(String username) {
        return loginRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }


    //storing logout timestamp when logout.
    public void saveUser(LoginModel user) {
        loginRepo.save(user);
    }
}

