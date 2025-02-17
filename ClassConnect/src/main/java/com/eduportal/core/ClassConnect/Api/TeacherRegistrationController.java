package com.eduportal.core.ClassConnect.Api;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.ProfileRepo;
import com.eduportal.core.ClassConnect.Teacher.teacherService.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/teachers/api")
public class TeacherRegistrationController{
    private final AuthenticationManager authenticationManager;
    private final ProfileRepo profileRepo;

    private final LoginService loginService;

    public TeacherRegistrationController(AuthenticationManager authenticationManager, LoginService loginService , ProfileRepo profileRepo){
        this.authenticationManager = authenticationManager;
        this.loginService = loginService;
        this.profileRepo = profileRepo;
    }

    /*@PostMapping("/register")
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
    }*/


    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        System.out.println("In login API");
        HashMap<String , String> message =  new HashMap<>();


        // Step 1: Check if the user exists
        Optional<ProfileModel> profileOptional = profileRepo.findByUsername(username);
        if (profileOptional.isEmpty()) {
            System.out.println("User not found: " + username);
            message.put("msg" , "User not found with username: "+ username);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        try {
            // Step 2: Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            System.out.println("Authentication successful for user: " + username);

            // Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Authentication context set.");

            // Step 3: Return the profile if authentication is successful
            return ResponseEntity.ok(profileOptional.get());

        } catch (BadCredentialsException ex) {
            // Step 4: Handle wrong password
            System.out.println("Invalid password for user: " + username);
            message.put("msg" , "Invalid username or password. Please check your credentials.");
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(message);
        } catch (Exception e) {
            // Step 5: Handle unexpected errors
            System.out.println("Authentication error: " + e.getMessage());
            message.put("msg" , "Authentication failed. Please try again later.");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(message);
        }
    }*/



    @PostMapping("/authentication")
    public ResponseEntity<?> authentication(@RequestParam String username, @RequestParam String password) {
        System.out.println("In login API");
        HashMap<String , String> message =  new HashMap<>();


        // Step 1: Check if the user exists
        Optional<ProfileModel> profileOptional = profileRepo.findByUsername(username);
        if (profileOptional.isEmpty()) {
            System.out.println("User not found: " + username);
            message.put("msg" , "User not found with username: "+ username);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        try {
            // Step 2: Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            System.out.println("Authentication successful for user: " + username);

            // Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Authentication context set.");
            message.put("msg" , "success");
            message.put("status"  , "1");

            // Step 3: Return the profile if authentication is successful
            return ResponseEntity.ok(message);

        } catch (BadCredentialsException ex) {
            // Step 4: Handle wrong password
            System.out.println("Invalid password for user: " + username);
            message.put("msg" , "Invalid username or password. Please check your credentials.");
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(message);
        } catch (Exception e) {
            // Step 5: Handle unexpected errors
            System.out.println("Authentication error: " + e.getMessage());
            message.put("msg" , "Authentication failed. Please try again later.");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(message);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(LoginModel user) {
        HashMap<String , String> message =  new HashMap<>();
        HashMap<String, Object> response =  new HashMap<>();
        Optional<ProfileModel> profileOptional = profileRepo.findByUsername(user.getUsername());
        if (profileOptional.isEmpty()) {
            System.out.println("User not found: " + user.getUsername());
            message.put("msg" , "User not found with username: "+ user.getUsername());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }
        System.out.println("here");
        String token = loginService.verifyToken(user);
        response.put("profile", profileOptional.get());
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
