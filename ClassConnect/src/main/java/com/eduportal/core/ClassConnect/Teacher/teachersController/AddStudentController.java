package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.AddStudentRepository;
import com.eduportal.core.ClassConnect.Teacher.teacherService.AddStudentService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (teacher_id == null) {
            redirectAttributes.addFlashAttribute("error", "Session expired or invalid. Please log in again.");
            return "redirect:/teachers/add_student"; // Redirect back to add student page
        }

        // Generate a 10-digit random student ID
        Integer student_id = generateRandomStudentId();
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
    private Integer generateRandomStudentId() {
        Random random = new Random();
        // Generate a random 10-digit number within the range of an Integer
        int randomStudentId = random.nextInt(900000000) + 100000000;  // Ensures a 9-digit ID
        return randomStudentId;
    }


    private String saveImage(String student_id, MultipartFile studentImage) {
        try {
            if (studentImage != null && !studentImage.isEmpty()) {
                // Use the student_id as the image file name (e.g., student_id.jpg)
                String imageDirectory = System.getProperty("user.dir") + "/uploads/images/students/";  // Path to the folder where images will be stored

                // Create the image file name using the student_id
                String imageName = student_id + ".jpg";  // Save the image as student_id.jpg

                // Create the file object with the full path (image name + path)
                File directory = new File(imageDirectory);
                if (!directory.exists()) {
                    directory.mkdirs();  // Create the directory if it doesn't exist
                }

                File imageFile = new File(imageDirectory + imageName);

                // Save the file to the specified directory
                studentImage.transferTo(imageFile);

                // Return the image URL or file path (relative or absolute path based on your setup)
                return imageDirectory + imageName;  // This will be the file path or URL for the image
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





}
