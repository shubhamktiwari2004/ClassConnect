package com.eduportal.core.ClassConnect.Admin.AdminService;


import com.eduportal.core.ClassConnect.Admin.AdminRepo.AddTeacherRepo;
import com.eduportal.core.ClassConnect.Api.ApiResponse;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.RegistrationProfileDetails;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.ProfileRepo;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.RegistrationProfileDetailsRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

@Service
public class AddTeacherService {

    @Autowired
    private JavaMailSender mailSender;

    private final AddTeacherRepo addTeacherRepo;
    private final ProfileRepo profileRepo;
    private final RegistrationProfileDetailsRepo profileDetailsRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public AddTeacherService(AddTeacherRepo addTeacherRepo, ProfileRepo profileRepo, RegistrationProfileDetailsRepo profileDetailsRepo) {
        this.addTeacherRepo = addTeacherRepo;
        this.profileRepo = profileRepo;
        this.profileDetailsRepo = profileDetailsRepo;
    }

    @Value("${file.upload-dir}")
    private String uploadDir;
    public ApiResponse addTeacher(String name , String username , String email , long mobile , String address , MultipartFile teacherImage){
        if(addTeacherRepo.existsByUsername(username)){
            throw new IllegalArgumentException("username already exists");
        }
        if (addTeacherRepo.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }

        //using current date time in seconds for unique teacher id.
        long epochSeconds =  Instant.now().getEpochSecond();
        long teacherId = epochSeconds;

        // Generate a random password
        String specialSymbol = "@";
        int randomNumber = (int) (Math.random() * 1_000_000_00); // Generate an 8-digit random number
        String rawPassword = username + specialSymbol + randomNumber;
        String encodedPassword = passwordEncoder.encode(rawPassword);


        LoginModel loginModel = new LoginModel();
        loginModel.setUsername(username);
        loginModel.setEmail(email);
        loginModel.setPassword(encodedPassword);
        loginModel.setTeacher_id((int) teacherId);


        try {
            // Handle image upload (if present)
            String imageUrl = null;

            System.out.println("images name :" + teacherImage);
            if (teacherImage != null && !teacherImage.isEmpty()) {
                String relativePath = "uploads/images/" + ((int) teacherId) + "/";

                // Save the image to a directory (you can modify the path as needed)
                Path path = Paths.get(uploadDir + ((int) teacherId) + "/" + teacherImage.getOriginalFilename());

                System.out.println("Creating directory: " + path.getParent());

                // Create directories if they don't exist
                Files.createDirectories(path.getParent());

                // Save the image
                teacherImage.transferTo(path.toFile());

                // Set the image URL (assuming you store the image path in the profile)
                imageUrl = relativePath + teacherImage.getOriginalFilename();
            }
            addTeacherRepo.save(loginModel);
            sendEmail(email, username, rawPassword);
            profileDetail(name , mobile , ((int) teacherId), email , username , address , imageUrl);
            return new ApiResponse(true, "Teacher registration successful");

        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse(false, "Teacher registration failed: " + e.getMessage());
        }
    }

    public void profileDetail(String name , long mobile , int teacher_id , String email , String username , String address , String imageUrl){


        System.out.println("in profile section3");
        RegistrationProfileDetails profileDetails = new RegistrationProfileDetails();
        profileDetails.setTeacher_id(teacher_id);
        profileDetails.setName(name);
        profileDetails.setEmail(email);
        profileDetails.setMobile(mobile);
        profileDetails.setUsername(username);
        profileDetailsRepo.save(profileDetails);



        ProfileModel profileModel = profileRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        profileModel.setAddress(address);
        if(imageUrl != null) {
            profileModel.setProfile_image(imageUrl);
        }


        profileRepo.save(profileModel);


    }

    // Method to send the email
    private void sendEmail(String recipientEmail, String username, String password) throws MessagingException {
        String subject = "Congratulations! Your Account Has Been Created";
        String message = "Dear Student,\n\n" +
                "Congratulations! Your account has been successfully created. Below are your login details:\n\n" +
                "Username: " + username + "\n" +
                "Password: " + password + "\n\n" +
                "Please keep this information secure and do not share it with anyone.\n\n" +
                "Best regards,\nClassConnect Team";

        // Create a MIME email message
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(recipientEmail);
        helper.setSubject(subject);
        helper.setText(message);

        // Send the email
        mailSender.send(mimeMessage);
    }

}
