package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.ProfileRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    ProfileRepo profile;

    public List<ProfileModel> viewProfile(int teacherId) { // Change int to Long
        return profile.getByTeacherId((long) teacherId);
    }

    @Transactional
    public void updateProfile(int teacherId, String name, String email, Long mobile, String address , String image) {
        profile.updateProfileById((long) teacherId, name, email, mobile, address , image);
    }
}
