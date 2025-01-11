package com.eduportal.core.ClassConnect.Api;

import com.eduportal.core.ClassConnect.Teacher.teacherService.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TeacherRegistrationController {

    private final LoginService loginService;

    public TeacherRegistrationController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/teachers/api/register")
    public ResponseEntity<Map<String,String>> register(
            @RequestParam (required = false) String name,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam (required = false) long mobile,
            @RequestParam String password
    ){

        HashMap<String , String> message =  new HashMap<>();

        if (username == null || username.trim().isEmpty()) {
            message.put("msg", "Username is required.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if (email == null || email.trim().isEmpty()) {
            message.put("msg", "Email is required.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if (password == null || password.trim().isEmpty()) {
            message.put("msg", "Email is required.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        try{
            ApiResponse isRegistered = loginService.signUp(name , username , email , password , mobile);
            if(isRegistered.isSuccess()){
                message.put("msg" , isRegistered.getMessage());
                return ResponseEntity.status(HttpStatus.CREATED).body(message);
            }else{
                if(!isRegistered.isSuccess()){
                    isRegistered.setMessage("Teacher Registration Failed.");
                    message.put("msg" , isRegistered.getMessage());
                    return ResponseEntity.status(HttpStatus.CREATED).body(message);
                }

            }
        }catch (IllegalArgumentException e) {
            message.put("msg" , e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }catch (Exception e) {
            message.put("msg" , e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
        message.put("msg" , "something went wrong....");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

}
