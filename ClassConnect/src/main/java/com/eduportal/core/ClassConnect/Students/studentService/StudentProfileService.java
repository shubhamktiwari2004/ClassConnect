package com.eduportal.core.ClassConnect.Students.studentService;

import com.eduportal.core.ClassConnect.Students.studentRepository.StudentProfileRepo;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import org.springframework.stereotype.Service;

@Service
public class StudentProfileService {

    private final StudentProfileRepo profileRepo;


    public StudentProfileService(StudentProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    public StudentLoginModel viewProfile(Long studentId){
       return profileRepo.viewProfile(studentId);
    }


    public void updateProfile(Long studentId, String firstName, String lastName, String email, String address, String imageUrl) {
        profileRepo.updateProfileById(studentId, firstName,lastName , email, address , imageUrl);

    }
}
