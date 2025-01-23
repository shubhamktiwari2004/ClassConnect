package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentMarksModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.AddStudentMarksRepo;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.AddStudentRepository;
import com.eduportal.core.ClassConnect.Teacher.teacherService.AddStudentService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class AddStudentController {

    @Autowired
    AddStudentService addStudentService;



    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/teacher/students")
    public String addStudents(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam("studentImage") MultipartFile studentImage,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Check if required fields are empty
        if (firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() ||
                email == null || email.isEmpty() ||
                address == null || address.isEmpty() ||
                studentImage == null || studentImage.isEmpty()) {

            redirectAttributes.addFlashAttribute("error", "All fields are required, including a valid image.");
            return "redirect:/teachers/add_student"; // Redirect back to add student page
        }
        // Retrieve teacher_id from the session
        Integer teacher_id = (Integer) session.getAttribute("teacher_id");
        System.out.println("Teacher ID from session: " + teacher_id);

        if (teacher_id == null) {
            redirectAttributes.addFlashAttribute("error", "Session expired or invalid. Please log in again.");
            return "redirect:/teachers/add_student"; // Redirect back to add student page
        }

        // Generate a 10-digit random student ID
        Long student_id = generateRandomStudentId();
        if (student_id == null) {
            redirectAttributes.addFlashAttribute("error", "Failed to generate a student ID. Please try again.");
            return "redirect:/teachers/add_student";
        }
        String studentIdString = String.valueOf(student_id);

        // Process the image file and check for a valid URL
        String imageUrl = saveImage(studentIdString, studentImage);
        if (imageUrl == null || imageUrl.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Failed to save the image. Please try uploading a valid image.");
            return "redirect:/teachers/add_student";
        }
        // Generate username and password
        String username = generateUsername(firstName);
        String password = generatePassword(firstName);

        // Log the generated credentials
        System.out.println("Generated username: " + username);
        System.out.println("Generated password: " + password);

        System.out.println("Validation passed: teacher_id=" + teacher_id + ", student_id=" + student_id + ", imageUrl=" + imageUrl);

        // Create and populate AddStudentModel
        AddStudentModel addStudentModel = new AddStudentModel();
        addStudentModel.setFirstName(firstName);
        addStudentModel.setLastName(lastName);
        addStudentModel.setEmail(email);
        addStudentModel.setAddress(address);
        addStudentModel.setTeacher_id(teacher_id);
        addStudentModel.setStudent_id(student_id);
        addStudentModel.setImage_url(imageUrl);
        addStudentModel.setIs_active("0");
        addStudentModel.setUser_name(username);
        addStudentModel.setPassword(password);

        // Save the student details to the database
        addStudentService.saveStudent(addStudentModel);

        // Send email with username and password
        try {
            sendEmail(email, username, password);
            redirectAttributes.addFlashAttribute("success", "Student added successfully!");
        } catch (MessagingException e) {
            redirectAttributes.addFlashAttribute("error", "Student added, but failed to send email.");
            e.printStackTrace();
        }

        // Log and confirm saving
        redirectAttributes.addFlashAttribute("success", "Student Added Succesfully");
        return "redirect:/teachers/students"; // Redirect to the student list or relevant page
    }

    // Helper method to generate a 10-digit random student ID
    private Long generateRandomStudentId() {
        Long epochSeconds = Instant.now().toEpochMilli();
        return epochSeconds;
    }


    @Value("${file.upload-dir}")
    private String uploadDir;
    private String saveImage(String student_id, MultipartFile studentImage) {
        try {
            if (studentImage != null && !studentImage.isEmpty()) {
                // Use the student_id as the image file name (e.g., student_id.jpg)
                String relativePath = "uploads/images/students/"+student_id+"/";  // Relative path for storing images

                Path path = Paths.get(uploadDir+"students/" + student_id + "/" + studentImage.getOriginalFilename());

                Files.createDirectories(path.getParent());

                studentImage.transferTo(path.toFile());

                // Return only the relative path
                return relativePath + studentImage.getOriginalFilename();  // Relative file path for the image
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception and return null or a default image URL if needed
        }
        return null;  // Return null if the image is not saved
    }

    // Helper method to generate a username
    private String generateUsername(String firstName) {
        Random random = new Random();
        String randomSuffix = String.valueOf(random.nextInt(900) + 100); // 3-digit random number
        return firstName.toLowerCase() + randomSuffix;
    }

    // Helper method to generate a password in the desired format
    private String generatePassword(String firstName) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();

        StringBuilder password = new StringBuilder();

        // Ensure the first name is at least 3 characters (for example 'remo')
        String namePart = firstName.toLowerCase().substring(0, Math.min(5, firstName.length()));  // Take up to 5 characters of the first name
        password.append(namePart);  // Add first part of the name to the password

        // Add the @ symbol after the name part
        password.append("@");

        // Generate random digits to complete the password length to 8 characters
        while (password.length() < 8) {
            int digit = random.nextInt(10);  // Generate a random number between 0 and 9
            password.append(digit);  // Append the random number
        }
        return password.toString();
    }

    //Method so send the email
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

    //Add marks to the student
    @PostMapping("/teachers/save_marks")
    public String saveMarks(@RequestParam List<Integer> unit1,
                            @RequestParam List<Integer> unit2,
                            @RequestParam List<Integer> sem1,
                            @RequestParam List<Integer> sem2,
                            @RequestParam List<String> subject,
                            @RequestParam (required = false) List<Long> id,
                            @RequestParam Long student_id)
       {
           System.out.println("Units1: " + unit1);
           System.out.println("Units2: " + unit2);
           System.out.println("Semesters1: " + sem1);
           System.out.println("Semesters2: " + sem2);
           System.out.println("subs: " + subject);
           System.out.println("IDs: " + id);
           System.out.println("Student ID: " + student_id);

           for(int i = 0 ; i<subject.toArray().length;i++){
               AddStudentMarksModel marksModel =  new AddStudentMarksModel();
               marksModel.setStudent_id(student_id);
               if(id != null && !id.isEmpty()) {
                   marksModel.setId(id.get(i));
               }
               marksModel.setSubject(subject.get(i));
               marksModel.setUnit1(unit1.get(i));
               marksModel.setUnit2(unit2.get(i));
               marksModel.setSem1(sem1.get(i));
               marksModel.setSem2(sem2.get(i));
               marksModel.setTotal((unit1.get(i)+unit2.get(i)+sem1.get(i)+sem2.get(i)));
               addStudentService.saveMarks(marksModel);
           }

        return "redirect:/teachers/students";
    }





}
