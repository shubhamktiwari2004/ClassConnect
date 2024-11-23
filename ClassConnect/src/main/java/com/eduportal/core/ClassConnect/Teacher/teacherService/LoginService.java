package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Config.SecurityConfig;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private LoginRepo loginRepo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public LoginModel signUp(String username , String email , String password){
        if(loginRepo.existsByUsername(username)){
            throw new IllegalArgumentException("username already exists");
        }
        if (loginRepo.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        //using current date time in seconds for unique teacher id.
        long epochSeconds = Instant.now().getEpochSecond();


        LoginModel loginmodel = new LoginModel();
        loginmodel.setUsername(username);
        loginmodel.setEmail(email);
        loginmodel.setPassword(passwordEncoder.encode(password));
        loginmodel.setTeacher_id((int) epochSeconds);

        return loginRepo.save(loginmodel);
    }

    /*public LoginModel login(String username , String password ){

        LoginModel loginModel = loginRepo.findByUsername(username)
                .or(() -> loginRepo.findByEmail(username))
                .orElseThrow(() -> new IllegalArgumentException("Invalid Username"));

//        if(!passwordEncoder.matches(password,loginModel.getPassword())){
//            throw new IllegalArgumentException("Invalid password");
//        }
        if(!passwordEncoder.matches(password , loginModel.getPassword())){
            throw new IllegalArgumentException("Invalid password");
        }

        return loginModel;
    }*/


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to load the user by username, if not found, check by email
        LoginModel loginModel = loginRepo.findByUsername(username)
                .or(() -> loginRepo.findByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(loginModel.getUsername())
                .password(loginModel.getPassword()) // Ideally, encode the password
                .roles("USER") // Add roles if applicable
                .build();
    }
}

