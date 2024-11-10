package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;

@Service
public class LoginService {

    @Autowired
    private LoginRepo loginRepo;

//    @Autowired
//    private PasswordEncoder passwordEncoder;


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
//        loginmodel.setPassword(passwordEncoder.encode(password));
        loginmodel.setPassword(password);
        loginmodel.setTeacher_id((int) epochSeconds);

        return loginRepo.save(loginmodel);
    }

    public LoginModel login(String username , String password ){

        LoginModel loginModel = loginRepo.findByUsername(username)
                .or(() -> loginRepo.findByEmail(username))
                .orElseThrow(() -> new IllegalArgumentException("Invalid Username"));

//        if(!passwordEncoder.matches(password,loginModel.getPassword())){
//            throw new IllegalArgumentException("Invalid password");
//        }
        if(!Objects.equals(password, loginModel.getPassword())){
                    throw new IllegalArgumentException("Invalid password");
                }

        return loginModel;
    }



}
