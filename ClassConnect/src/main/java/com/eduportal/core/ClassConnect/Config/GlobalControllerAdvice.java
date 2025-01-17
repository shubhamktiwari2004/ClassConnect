package com.eduportal.core.ClassConnect.Config;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.ProfileRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;

@ControllerAdvice
public class GlobalControllerAdvice {


    private final ProfileRepo profileRepo;  // Assuming this is your JPA repository

    public GlobalControllerAdvice(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    @ModelAttribute("teacherData")
    public ArrayList<ProfileModel> populateTeacherData(HttpSession session) {
        Integer teacherId = (Integer) session.getAttribute("teacher_id");  // Get the teacher_id as Integer
        if (teacherId != null) {
            return (ArrayList<ProfileModel>) profileRepo.getByTeacherId(teacherId.longValue()); // Convert to long
        }
        return new ArrayList<>();
    }
}
